package com.marcos.exams.services;

import java.util.List;
import java.util.Optional;

import com.marcos.exams.entities.User;
import com.marcos.exams.validations.UserFoundException;

public interface UserService {
	
	public User saveUser(User user) throws UserFoundException;
	
	public Optional<User> getUser(String username);
	
	public void deleteUser(Long userId);
	
	public List<User> getUsers();
	
	public Optional<User> findById(Long id);

}
