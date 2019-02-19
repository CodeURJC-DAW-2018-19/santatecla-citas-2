package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationController {

    String register(User user, HttpServletRequest request);

}
