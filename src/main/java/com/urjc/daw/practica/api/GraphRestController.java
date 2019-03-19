package com.urjc.daw.practica.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.urjc.daw.practica.service.impl.GraphService;

@RestController
@RequestMapping("api/chart")
public class GraphRestController {
	
	@Autowired
	GraphService graphService;

	@GetMapping("")
	public ResponseEntity<HashMap<String,Integer>> getGraph(){
		HashMap<String,Integer> found = graphService.getGraph();
		if(!found.isEmpty()) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
