package com.urjc.daw.practica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;

@Component
public class DBInitializer {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		// Sample users
		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}

}
