package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public interface QuoteController {

    String findOne();

    String findAll();

    String postQuote(Model model,Quote quote);

    String editQuote(Quote quote);

    public String deleteQuote(Quote quote,@PathVariable long id);
}
