package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.AuthenticationController;
import com.urjc.daw.practica.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationControllerImpl implements AuthenticationController {
    @Override
    public String register(User user, HttpServletRequest request) {
        return null;
    }

    @Override
    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return new ModelAndView("login");
    }
}
