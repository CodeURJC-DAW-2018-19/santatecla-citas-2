package com.urjc.daw.practica.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController{
    
	@GetMapping("/")
	public ModelAndView showIndex(Model model) {
		//Comentario mordaz y sarcástico para comprobar por que este hijo de mala madre
		//no sabe que es un maldito get
		ModelAndView mav = new  ModelAndView("index");
		System.out.println(mav.toString());
		return mav;
	}
	
	@GetMapping("/error")
	public ModelAndView showError(Model model) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}