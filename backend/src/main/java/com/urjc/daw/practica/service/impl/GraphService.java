package com.urjc.daw.practica.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urjc.daw.practica.model.Topic;

@Service
public class GraphService {

	@Autowired
	TopicManagementServiceImpl topicService;
	
	public HashMap<String,Integer> getGraph(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		Pageable wholePage = Pageable.unpaged();
		Iterator<Topic> it = topicService.findAll(wholePage.getPageNumber(),wholePage.getPageSize()).iterator();
		while(it.hasNext()) {
			Topic topic = (Topic) it.next();
			map.put(topic.getName(), topic.getnQuotes());
		}
		return map;
	}
	
}
