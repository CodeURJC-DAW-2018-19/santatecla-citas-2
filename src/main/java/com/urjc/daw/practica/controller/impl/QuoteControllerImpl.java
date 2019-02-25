package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.security.UserComponent;
import com.urjc.daw.practica.service.QuoteManagementService;

import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuoteControllerImpl implements QuoteController {

    private static final int QUOTES_PER_PAGE=10;

    
    @Autowired
    QuoteManagementService quoteService;

    @Autowired
    TopicManagementService topicService;
    
    @Autowired
    UserComponent userComponent;
    
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

    @Override
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.GET)
    public String findOne() {
        //ToDo Pedir a quote service que devuelva el quote segun el id
        return "quoteForm";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.GET)
    public String findAll() {
        //quoteService.findAll(nPage,QUOTES_PER_PAGE);
        quoteService.findAll();
        
        return "quote";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.POST)
    public String postQuote(Model model,Quote quote) {
    	quoteService.save(quote);
        model.addAttribute("cod","creada");
        return "quoteCreated";
    }

    @Override
    @GetMapping("/editQuote/{id}")
    public String editQuote(Model model,@PathVariable long id) {
        Optional<Quote> quote = quoteService.findOne(id);
        
        if(quote.isPresent()) {
        	model.addAttribute("quote",quote.get());
        }
        return "quoteForm";
    }

    @Override
    @GetMapping("/deleteQuote/{id}")
    public String deleteQuote(Model model,@PathVariable long id) {
        quoteService.deleteQuote(id);
        model.addAttribute("cod","eliminada");
        return "quoteCreated";
    }

    @Override
    @GetMapping("/searchQuote")
    public String findByKeyword(@RequestParam(value = "keyword") String keyword, Model model) {
        model.addAttribute("quote",quoteService.findByKeyword(keyword));
        model.addAttribute("topic",topicService.findAll());
        model.addAttribute("quoteKeyword",keyword);
        return "index";
    }


}
