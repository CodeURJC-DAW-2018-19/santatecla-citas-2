package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserController {


    String register(Model model, User user);
}
