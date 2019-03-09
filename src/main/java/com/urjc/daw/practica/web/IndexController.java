package com.urjc.daw.practica.web;

import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.urjc.daw.practica.security.UserComponent;
import com.urjc.daw.practica.service.QuoteManagementService;

@RestController
public class IndexController{

	private static final int DEFAULT_PAGE=0;
	private static final int QUOTES_PER_PAGE=10;
	@Autowired
	private QuoteManagementService qms;

	@Autowired
	private TopicManagementService topicService;
	
	@Autowired
	private UserComponent userComponent;
	
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("role", userComponent.getLoggedUser().toString());
			model.addAttribute("username",userComponent.getLoggedUser().getName());
			if(userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")){
				model.addAttribute("admin",userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
				model.addAttribute("user",userComponent.getLoggedUser().getRoles().contains("ROLE_USER"));
			}
		}
	}
	
	
	@GetMapping("/")
	public ModelAndView showIndex(Model model) {
		ModelAndView mav = new  ModelAndView("index");
		model.addAttribute("quote", qms.findAll(DEFAULT_PAGE,QUOTES_PER_PAGE));
		model.addAttribute("topic",topicService.findAll());
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView showLogin(Model model) {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@GetMapping("/loginerror")
	public ModelAndView showLoginError(Model model) {
		ModelAndView mav = new ModelAndView("loginerror");
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView showLogoutScreen(Model model) {
		ModelAndView mav = new ModelAndView("logout");
		return mav;
	}
	
	@GetMapping("/quoteForm")
	public ModelAndView showQuoteForm(Model model) {
		return new ModelAndView("quoteForm");
	}
	
	@GetMapping("/topicForm")
	public ModelAndView showTopicForm(Model model) {
		ModelAndView newModel = new ModelAndView("topicForm");
		model.addAttribute("quote",qms.findAll(DEFAULT_PAGE, QUOTES_PER_PAGE));
		return newModel;
	}

	
	@GetMapping("/graph")
	public ModelAndView showGraph(Model model) {
		ModelAndView newModel = new ModelAndView("graphs");
		return newModel;
	}
	
	@GetMapping("/error")
	public ModelAndView showError(Model model) {
		ModelAndView newModel = new ModelAndView("error");
		return newModel;
	}

}