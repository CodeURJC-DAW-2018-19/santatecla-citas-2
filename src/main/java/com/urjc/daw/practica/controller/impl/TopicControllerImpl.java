package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TopicController;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.security.UserComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import com.urjc.daw.practica.service.impl.DocumentGenerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicControllerImpl implements TopicController {

	private static final int DEFAULT_PAGE = 0;
	private static final int QUOTES_PER_PAGE = 10;

	@Autowired
	private TopicManagementService topicService;

	@Autowired
	private QuoteManagementService quoteService;

	@Autowired
	private UserComponent userComponent;

	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if (logged) {
			model.addAttribute("role", userComponent.getLoggedUser().toString());
			model.addAttribute("username", userComponent.getLoggedUser().getName());
			if (userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")) {
				model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
				model.addAttribute("user", userComponent.getLoggedUser().getRoles().contains("ROLE_USER"));
			}
		}
	}

	@Override
	public Topic getTopic() {
		return null;
	}

	@Override
	public List<Topic> getAllTopics() {
		return null;
	}

	@Override
	@PostMapping("/createTopic")
	public String saveTopic(Model model, Topic topic) {
		topicService.save(topic);

		model.addAttribute("cod", "El tema ha sido creado");
		return "created";
	}

	@Override
	@GetMapping("/topic/{id}")
	public String editTopic(Model model, @PathVariable long id) {
		Optional<Topic> topic = topicService.findOne(id);

		if (topic.isPresent()) {
			Topic currentTopic = topic.get();
			List<Quote> quotesReferenced = topicService.getReferencedQuotes(currentTopic);
			model.addAttribute("name", currentTopic.getName());
			model.addAttribute("quoteReference", quotesReferenced);
			model.addAttribute("textReference", currentTopic.getTexts());
			model.addAttribute("topic", currentTopic);
			if (((List<Quote>) model.asMap().get("quoteReference")).isEmpty()) {
				model.addAttribute("quote", quoteService.findAll(DEFAULT_PAGE, QUOTES_PER_PAGE));
			} else {
				model.addAttribute("quote", quoteService.findByIdDiferrentThan(currentTopic.getQuoteIds()));
			}
		}
		return "topicForm";
	}

	@Override
	@GetMapping("/deleteTopic/{id}")
	public String deleteTopic(Model model, @PathVariable Long id) {
		Optional<Topic> current = topicService.findOne(id);
		if (current.isPresent()) {
			topicService.deleteTopic(current.get());
			model.addAttribute("cod", "El tema ha sido eliminado");
			return "created";
		} else {
			model.addAttribute("cod", "error al borrar, no encontrado");
			return "created";
		}

	}

	@Override
	@GetMapping("/searchTopic")
	public String findByKeyword(@RequestParam(value = "keyword") String keyword, Model model) {
		model.addAttribute("quote", quoteService.findAll(DEFAULT_PAGE, QUOTES_PER_PAGE));
		model.addAttribute("topic", topicService.findByKeyword(keyword));
		return "index";
	}

	@Autowired
	DocumentGenerationService dgs;

	@GetMapping("/generatePDF/{id}")
	public ResponseEntity<ByteArrayResource> generatePDF(Model model, @PathVariable Long id) {
		Topic topic = topicService.findOne(id).get();
		File file = null;
		ByteArrayResource resource = null;
		try {
			file = dgs.generateDocument(topic);
			Path path = Paths.get(file.getAbsolutePath());
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=topicContent.pdf");
		
		return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);

	}
}
