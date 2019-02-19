package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Quote;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface QuoteController {

    String findOne();

    String findAll();

    String postQuote(Quote quote);

    String editQuote(Quote quote);

    String deleteQuote(Quote quote);
}
