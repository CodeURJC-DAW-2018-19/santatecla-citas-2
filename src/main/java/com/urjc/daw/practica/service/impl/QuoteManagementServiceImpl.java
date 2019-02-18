package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuoteManagementServiceImpl implements QuoteManagementService {

    @Autowired
    QuoteRepository quotes;

    @Override
    public Quote getQuote(Long id) {
        return quotes.findQuoteById(id);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quotes.findAll();
    }

//    @Override
//    public List<Quote> getQuotesByTheme(Topic topic) {
//        return quotes.findByThemesContains(topic);
//    }

    @Override
    public Quote editQuote(Quote quote) {
        return quotes.save(quote);
    }

    @Override
    public Quote deleteQuote(Quote quote) {
        quotes.delete(quote);
        return quote;
    }
}
