package com.marcos.exams;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcos.exams.entities.Role;
import com.marcos.exams.entities.User;
import com.marcos.exams.services.UserService;

@SpringBootApplication
public class ExamsBackendApplication implements CommandLineRunner {
	
	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamsBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
