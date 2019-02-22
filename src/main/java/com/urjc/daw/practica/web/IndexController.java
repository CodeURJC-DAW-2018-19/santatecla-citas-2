package com.urjc.daw.practica.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{
    
	@GetMapping("/")
	public String showBooks(Model model) {
		//Comentario mordaz y sarcástico para comprobar por que este hijo de mala madre
		//no sabe que es un maldito get
		System.out.println("pasa por aquí");	
		return "index";
	}

}