package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Service;

public interface TopicManagementService {

    Topic save(Topic topic);
}
