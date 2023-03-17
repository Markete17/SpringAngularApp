package com.marcos.exams.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.exams.entities.Role;
import com.marcos.exams.entities.User;
import com.marcos.exams.repositories.RolRepository;
import com.marcos.exams.repositories.UserRepository;
import com.marcos.exams.validations.UserFoundException;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public User saveUser(User user){
		Optional<User> localUser = userRepository.findByUsername(user.getUsername());
		
		if (!localUser.isPresent()) {
			user.setProfile("default.png");
			Set<Role> roles = new HashSet<>();
			Optional<Role> role = this.rolRepository.findById(2L);
			if(role.isPresent()) {
				roles.add(role.get());
				user.setRoles(roles);
			}
			return this.userRepository.save(user);
		}
		
		throw new UserFoundException();
	
	}

	@Override
	public Optional<User> getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}

}
