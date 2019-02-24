package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TopicController;
import com.urjc.daw.practica.model.Topic;

import java.util.List;
import java.util.Optional;

import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String postTopic(Model model,Topic topic) {
        topicService.save(topic);
        model.addAttribute("cod","creado");
        return "topicCreated";
    }

    @Override
    @GetMapping("/editTopic/{id}")
    public String editTopic(Model model, @PathVariable long id) {
        Optional<Topic> topic = topicService.findOne(id);

        if(topic.isPresent()) {
            model.addAttribute("topic",topic.get());
        }
        return "topicForm";
    }

    @Override
    public boolean deleteTopic(Topic Topic) {
        return false;
    }
}
