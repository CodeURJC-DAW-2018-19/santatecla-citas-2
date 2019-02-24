package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.repository.TopicRepository;
import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicManagementServiceImpl implements TopicManagementService {
    @Autowired
    TopicRepository topics;

    @Override
    public Topic save(Topic topic) {

        topics.save(topic);
        return null;
    }

    @Override
    public List<Topic> findAll() {
        return topics.findAll();
    }
}
