package com.urjc.daw.practica;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;
import com.urjc.daw.practica.security.UserAuthProvider;
import com.urjc.daw.practica.service.impl.QuoteManagementServiceImpl;
import com.urjc.daw.practica.service.impl.TopicManagementServiceImpl;

@Component
public class DBInitializer {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuoteManagementServiceImpl quoteService;

	@Autowired
	private TopicManagementServiceImpl topics;

	@Autowired
	UserAuthProvider authProvider;
	
	@Autowired
    private BCryptPasswordEncoder passEncoder;


	@PostConstruct
	public void init() {

		// Sample Quotes
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Hola, me llamo Íñigo Montoya, tu mataste a mi padre, preparate a morir","Íñigo Montoya", "The Princess Bride"));
		quoteService.save(new Quote("Que va si no estaba durmiendo, solo estaba mirando pa´dentro","Tragabuche", "Bandolero"));
		quoteService.save(new Quote("Self discipline is self love","Will Smith", "Video"));
		quoteService.save(new Quote("Get busy living or get busy dying","Stephen King", "The Shawshank Redemption"));
		quoteService.save(new Quote("Experience is merely the name men gave to their mistakes","Oscar Wilde", "The Picture of Dorian Gray"));
		quoteService.save(new Quote("All men dream: but not equally. Those who dream by night in the dusty recesses of their minds wake in the day to find that it was vanity: but the dreamers of the day are dangerous men, for they may act their dreams with open eyes, to make it possible. This I did","Lawrence of Arabia", "Seven Pilars of Wisdom"));
		quoteService.save(new Quote("Allé voy","Cloud", "Final Fantasy VII"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		quoteService.save(new Quote("Did I ever tell you the definition of insanity?..","Vaas Montenegro", "Far Cry 3"));
		
		//SampleTopics
				
		List<Long> quoteId = new ArrayList<Long>();
		quoteId.add(quoteService.findOne(2L).get().getId());
		quoteId.add(quoteService.findOne(3L).get().getId());
		quoteId.add(quoteService.findOne(4L).get().getId());
		List<String> textList = new ArrayList<String>();
		textList.add("Esto es un texto1");
		textList.add("Esto es un texto2");
		textList.add("Esto es un texto3");
		textList.add("Esto es un texto4");
		
		topics.save(new Topic("Test"));
		topics.save(new Topic("Test2",quoteId,textList));
		
		quoteId.add(quoteService.findOne(5L).get().getId());
		quoteId.add(quoteService.findOne(6L).get().getId());
		textList.add("Esto es un texto5");
		textList.add("Esto es un texto6");
		
		topics.save(new Topic("Test3",quoteId,textList));
		
		quoteId.clear();
		quoteId.add(quoteService.findOne(5L).get().getId());
		quoteId.add(quoteService.findOne(6L).get().getId());
		textList.clear();
		textList.add("Esto es un texto2");
		textList.add("Esto es un texto3");
		
		topics.save(new Topic("Test4",quoteId,textList));

		// Sample users
		userRepository.save(new User("user", passEncoder.encode("pass"), "ROLE_USER"));
		userRepository.save(new User("admin",passEncoder.encode("pass"), "ROLE_USER", "ROLE_ADMIN"));
	}

}
