package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TopicController;
import com.urjc.daw.practica.model.Topic;

import java.util.List;

import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicControllerImpl implements TopicController {

	@Autowired
    TopicManagementService topicService;
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
    public String postTopic(Topic topic) {
        topicService.save(topic);
        return "index";
    }

    @Override
    public boolean editTopic(Topic Topic) {
        return false;
    }

    @Override
    public boolean deleteTopic(Topic Topic) {
        return false;
    }
}
