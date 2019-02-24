package com.urjc.daw.practica.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.security.UserComponent;
import com.urjc.daw.practica.service.QuoteManagementService;

@RestController
public class IndexController{
    
	@Autowired
	private QuoteManagementService qms;
	
	@Autowired
	private UserComponent userComponent;
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("role", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("username",userComponent.getLoggedUser().getName());
		}
	}
	
	
	@GetMapping("/")
	public ModelAndView showIndex(Model model) {
		ModelAndView mav = new  ModelAndView("index");
		model.addAttribute("quote", qms.findAll());
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView showLogin(Model model) {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@GetMapping("/loginerror")
	public ModelAndView showError(Model model) {
		ModelAndView mav = new ModelAndView("loginerror");
		return mav;
	}
	
	@GetMapping("/quoteForm")
	public ModelAndView showQuoteForm(Model model) {
		return new ModelAndView("quoteForm");
	}
	
	@GetMapping("/topicForm")
	public ModelAndView showTopicForm(Model model) {
		ModelAndView newModel = new ModelAndView("topicForm");
		model.addAttribute("quote",qms.findAll());
		return newModel;
	}
	

}