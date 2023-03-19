package com.marcos.exams.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marcos.exams.services.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtUtils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				System.out.println("Token Expired");
			} catch (Exception e) {
				
			}
		} else {
			System.out.println("Invalid Token");
		}
		
		if (username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = this.userService.loadUserByUsername(username);
			
			if(this.jwtUtils.validateToken(jwtToken, user)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			} else {
				System.out.println("Invalid Token");
			}
			filterChain.doFilter(request, response);
		}
		
	}

}
