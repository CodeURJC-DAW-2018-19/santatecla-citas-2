package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuoteManagementService {
    Quote getQuote(Long id);
    List<Quote> getAllQuotes();
   // List<Quote> getQuotesByTheme(Topic topic);

    Quote editQuote(Quote quote);

    Quote deleteQuote(Quote quote);
}
