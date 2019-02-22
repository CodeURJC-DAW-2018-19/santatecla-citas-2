package com.urjc.daw.practica.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.urjc.daw.practica.service.QuoteManagementService;

@RestController
public class IndexController{
    
	@Autowired
	private QuoteManagementService qms;
	
	
	@GetMapping("/")
	public ModelAndView showIndex(Model model) {
		//Comentario mordaz y sarcástico para comprobar por que este hijo de mala madre
		//no sabe que es un maldito get
		ModelAndView mav = new  ModelAndView("index");
		
		model.addAttribute("quote", qms.findAll());
		System.out.println(mav.toString());
		System.out.println(qms.findAll().toString());
		return mav;
	}
	
	@GetMapping("/error")
	public ModelAndView showError(Model model) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}