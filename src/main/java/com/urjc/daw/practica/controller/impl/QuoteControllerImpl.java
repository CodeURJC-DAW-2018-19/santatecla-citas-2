package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.service.QuoteManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class QuoteControllerImpl implements QuoteController {

    private static final int QUOTES_PER_PAGE=10;

    @Autowired
    QuoteManagementService quoteService;

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
}
