package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public interface TopicController {

    Topic getTopic();
    List<Topic> getAllTopics();

    String postTopic(Model model,Topic topic);

    String editTopic(Model model, @PathVariable long id);

    String deleteTopic(Model model, @PathVariable Long id);
}
