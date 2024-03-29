package com.urjc.daw.practica.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.urjc.daw.practica.service.impl.TopicManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import com.urjc.daw.practica.service.impl.DocumentGenerationService;

@RestController
@RequestMapping("/api/topics")
public class TopicRestController {

	private static final int DEFAULT_PAGE = 0;
	private static final int TOPICS_PER_PAGE = 10;
	private static final String FILENAME = "topicContent.pdf";

	@Autowired
	private TopicManagementServiceImpl topicService;
	
	@Autowired
	DocumentGenerationService dgs;
	
	@GetMapping("/{id}")
	public ResponseEntity<Topic> getTopic(@PathVariable long id) {
		Topic found = topicService.findOne(id).get();
		if(found != null) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("")
	public ResponseEntity<List<Topic>> getTopics() {
		List<Topic> found = topicService.findAllUnpaged();
		if(found != null) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(params = {"page"}) 
	public ResponseEntity<Page<Topic>> getAllTopics(@RequestParam("page") int nPag) {
		Page<Topic> pageable = topicService.findAll(DEFAULT_PAGE,TOPICS_PER_PAGE);
		if(pageable.hasContent()) {
			return new ResponseEntity<>(pageable,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Topic saveTopic(@RequestBody Topic topic) {
		topicService.save(topic);
		return topic;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Topic> editTopic(@PathVariable long id,@RequestBody Topic topic) {
		Optional<Topic> found = topicService.findOne(id);
		if(found.isPresent()) {
			topic.setId(id);
			topicService.save(topic);
			return new ResponseEntity<>(found.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Topic> deleteTopic(@PathVariable long id) {
		Optional<Topic> deleted = topicService.findOne(id);
		if(deleted.isPresent()) {
			topicService.deleteTopic(deleted.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(params = {"keyword"}) 
	public ResponseEntity<List<Topic>> findByKeyword(@RequestParam(value = "keyword") String keyword) {
		List<Topic> found = topicService.findByKeyword(keyword);
		if(!found.isEmpty()) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/pdf/{id}")
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+FILENAME);
		
		return ResponseEntity.ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);

	}

}
