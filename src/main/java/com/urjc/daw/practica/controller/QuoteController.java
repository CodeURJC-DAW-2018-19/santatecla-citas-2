package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Quote;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface QuoteController {

    Quote getQuote();
    List<Quote> getAllQuotes();

    boolean postQuote(Quote quote);

    boolean editQuote(Quote quote);

    boolean deleteQuote(Quote quote);
}
