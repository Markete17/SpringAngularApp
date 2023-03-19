package com.marcos.exams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.marcos.exams.entities.User;
import com.marcos.exams.validations.UserFoundException;

public interface UserService extends UserDetailsService {
	
	public User saveUser(User user) throws UserFoundException;
	
	public Optional<User> getUser(String username);
	
	public void deleteUser(Long userId);
	
	public List<User> getUsers();
	
	public Optional<User> findById(Long id);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
