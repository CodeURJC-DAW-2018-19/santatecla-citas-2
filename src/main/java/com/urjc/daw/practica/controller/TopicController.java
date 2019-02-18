package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Theme;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ThemeController {

    Theme getTheme();
    List<Theme> getAllThemes();

    boolean postText(Theme theme);

    boolean editTheme(Theme theme);

    boolean deleteTheme(Theme theme);
}
