package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuoteManagementService {
    Optional<Quote> findOne(Long id);
    //Page<Quote> findAll(int nPage, int nQuotes);

    List<Quote> findAll();

    Quote save(Quote quote);

    Quote editQuote(Quote quote);

    Quote deleteQuote(Quote quote);
}
