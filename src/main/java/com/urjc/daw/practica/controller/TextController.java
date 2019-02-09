package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Text;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface TextController {

    Text getText();
    List<Text> getAllTexts();

    boolean postText(Text text);

    boolean editText(Text text);

    boolean deleteText(Text text);
}
