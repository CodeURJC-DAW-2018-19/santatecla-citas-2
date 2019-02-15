package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TopicController;
import com.urjc.daw.practica.model.Topic;

import java.util.List;

public class TopicControllerImpl implements TopicController {
    @Override
    public Topic getTheme() {
        return null;
    }

    @Override
    public List<Topic> getAllThemes() {
        return null;
    }

    @Override
    public boolean postText(Topic topic) {
        return false;
    }

    @Override
    public boolean editTheme(Topic topic) {
        return false;
    }

    @Override
    public boolean deleteTheme(Topic topic) {
        return false;
    }
}
