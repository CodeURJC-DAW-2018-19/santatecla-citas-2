package com.urjc.daw.practica.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import com.urjc.daw.practica.service.impl.DocumentGenerationService;

@Controller
@RequestMapping("/api/topic")
public class TopicRestController {

	private static final int DEFAULT_PAGE = 0;
	private static final int QUOTES_PER_PAGE = 10;

	@Autowired
	private TopicManagementService topicService;

	@Autowired
	private QuoteManagementService quoteService;
	
	public Topic getTopic() {
		return null;
	}

	public List<Topic> getAllTopics() {
		return null;
	}

	@PostMapping("/createTopic")
	@ResponseStatus(HttpStatus.CREATED)
	public Topic saveTopic(@RequestBody Topic topic) {
		topicService.save(topic);
		return topic;
	}


	@PutMapping("/{id}")
	public Topic editTopic(@PathVariable long id,@RequestBody Topic topic) {
		topicService.findOne(id).get();
		topic.setId(id);
		topicService.save(topic);
		return topic;
	}


	@DeleteMapping("/deleteTopic/{id}")
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

	@GetMapping("/searchTopic")
	public List<Topic> findByKeyword(@RequestParam(value = "keyword") String keyword) {
		return topicService.findByKeyword(keyword);
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