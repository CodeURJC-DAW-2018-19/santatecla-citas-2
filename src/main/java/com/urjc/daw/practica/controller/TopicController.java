package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface TopicController {

    Topic getTopic();
    List<Topic> getAllTopics();

    boolean postText(Topic topic);

    boolean editTopic(Topic topic);

    boolean deleteTopic(Topic topic);
}
