package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface TopicController {

    Topic getTheme();
    List<Topic> getAllThemes();

    boolean postText(Topic topic);

    boolean editTheme(Topic topic);

    boolean deleteTheme(Topic topic);
}
