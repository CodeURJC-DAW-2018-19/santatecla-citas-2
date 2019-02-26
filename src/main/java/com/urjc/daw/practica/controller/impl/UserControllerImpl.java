package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.UserController;
import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class UserControllerImpl implements UserController {

    @Autowired
    UserRepository users;

    @Override
    @PostMapping("/register")
    public String register(Model model, User user) {

        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        users.save(user);
        model.addAttribute("cod","El usuario ha sido registrado");
        return "created";
    }
}
