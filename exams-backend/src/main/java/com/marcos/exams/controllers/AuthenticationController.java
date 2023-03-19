package com.marcos.exams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.exams.security.JwtRequest;
import com.marcos.exams.security.JwtResponse;
import com.marcos.exams.security.JwtUtils;
import com.marcos.exams.services.UserService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		} catch (UsernameNotFoundException  e) {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		UserDetails userDetails = this.userService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException disabledException) {
			throw new Exception("DISABLED USER "+disabledException.getMessage());
		} catch (BadCredentialsException badCredentialsException) {
			throw new Exception("BAD CREDENTIALS"+badCredentialsException.getMessage());
		}
	}

}
