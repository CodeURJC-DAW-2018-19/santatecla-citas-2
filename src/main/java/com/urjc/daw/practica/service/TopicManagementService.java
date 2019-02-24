package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicManagementService {

    Topic save(Topic topic);
    List<Topic> findAll();
}
