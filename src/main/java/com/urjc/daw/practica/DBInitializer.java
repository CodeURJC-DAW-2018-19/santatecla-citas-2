package com.urjc.daw.practica;

import javax.annotation.PostConstruct;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.repository.TextRepository;
import com.urjc.daw.practica.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;

@Component
public class DBInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuoteRepository quotes;

	@Autowired
	private TopicRepository topics;

	@Autowired
	private TextRepository texts;

	@Autowired
	UserAuthProvider authProvider;
	
	@Autowired
    private BCryptPasswordEncoder passEncoder;


	@PostConstruct
	public void init() {
		
		//SampleTopics
		topics.save(new Topic());
		
		// Sample Quotes
		quotes.save(new Quote("Did I ever tell you the definition of insanity","Vaas Montenegor", "The Bible"));
		quotes.save(new Quote("Did I ever tell you the definition of insanity","Vaas Montenegor", "The Bible"));
		quotes.save(new Quote("Did I ever tell you the definition of insanity","Vaas Montenegor", "The Bible"));
		// Sample users
		userRepository.save(new User("user", passEncoder.encode("pass"), "ROLE_USER"));
		userRepository.save(new User("admin",passEncoder.encode("pass"), "ROLE_USER", "ROLE_ADMIN"));
	}

}
