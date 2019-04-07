package com.urjc.daw.practica.api;

import java.util.ArrayList;

import com.urjc.daw.practica.model.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.urjc.daw.practica.service.impl.ChartService;

@RestController
@RequestMapping("api/chart")
public class ChartRestController {
	
	@Autowired
	ChartService chartService;

	@GetMapping("")
	public ResponseEntity<ArrayList<Chart>> getGraph(){
		ArrayList<Chart> found = chartService.getChart();
		if(!found.isEmpty()) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
