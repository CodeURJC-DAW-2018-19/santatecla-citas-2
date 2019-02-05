package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TextController;
import com.urjc.daw.practica.model.Text;

import java.util.List;

public class TextControllerImpl implements TextController {

    @Override
    public Text getText() {
        return null;
    }

    @Override
    public List<Text> getAllTexts() {
        return null;
    }

    @Override
    public boolean postText(Text text) {
        return false;
    }

    @Override
    public boolean editText(Text text) {
        return false;
    }

    @Override
    public boolean deleteText(Text text) {
        return false;
    }
}
