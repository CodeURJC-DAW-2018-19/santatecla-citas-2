package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.security.UserComponent;
import com.urjc.daw.practica.service.QuoteManagementService;

import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class QuoteControllerImpl implements QuoteController {

    private static final int QUOTES_PER_PAGE=10;

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/quote/");



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
    @RequestMapping(value = "/nPag")
    public ResponseEntity<List<Quote>> findAll(@PathVariable int nPag) {

        return new ResponseEntity<>(quoteService.findAll(nPag,QUOTES_PER_PAGE), HttpStatus.OK);

    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.POST)
    public String postQuote(Model model, Quote quote,
                            @RequestParam("file") MultipartFile file) {
    	quoteService.saveQuoteImage(file,quote);
        model.addAttribute("cod","La cita ha sido creada");
        return "created";
    }

    @Override
    @GetMapping("/quote/{id}")
    public String editQuote(Model model,@PathVariable long id) {
        Optional<Quote> quote = quoteService.findOne(id);
        
        if(quote.isPresent()) {
        	model.addAttribute("quote",quote.get());
        	model.addAttribute("rnd",Math.random());
        }
        return "quoteForm";
    }

    @Override
    @GetMapping("/deleteQuote/{id}")
    public String deleteQuote(Model model,@PathVariable long id) {
        quoteService.deleteQuote(id);
        model.addAttribute("cod","La cita ha sido eliminada");
        return "created";

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
