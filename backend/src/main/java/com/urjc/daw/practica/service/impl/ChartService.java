package com.urjc.daw.practica.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.urjc.daw.practica.model.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urjc.daw.practica.model.Topic;

@Service
public class ChartService {

	@Autowired
	TopicManagementServiceImpl topicService;
	
	public ArrayList<Chart> getChart(){
		ArrayList<Chart> map = new ArrayList<>();
		Pageable wholePage = new PageRequest(0,Integer.MAX_VALUE);
		Iterator<Topic> it = topicService.findAll(wholePage.getPageNumber(),wholePage.getPageSize()).iterator();
		while(it.hasNext()) {
			Topic topic = (Topic) it.next();
			map.add(new Chart(topic.getName(), topic.getnQuotes()));
		}
		return map;
	}
	
}
