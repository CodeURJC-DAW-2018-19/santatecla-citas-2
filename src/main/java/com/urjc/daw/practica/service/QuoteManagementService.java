package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuoteManagementService {
    Quote getQuote(Long id);
    Page<Quote> findAll(int nPage, int nQuotes);
   // List<Quote> getQuotesByTheme(Topic topic);

    Quote save(Quote quote);

    Quote editQuote(Quote quote);

    Quote deleteQuote(Quote quote);
}
