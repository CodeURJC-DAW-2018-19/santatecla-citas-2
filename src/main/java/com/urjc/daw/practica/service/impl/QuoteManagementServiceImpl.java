package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuoteManagementServiceImpl implements QuoteManagementService {

    @Autowired
    QuoteRepository quotes;

    @Override
    public Optional<Quote> findOne(Long id) {
        return quotes.findQuoteById(id);
    }

    /*@Override
    public Page<Quote> findAll(int nPage, int nQuotes) {
        return quotes.findAll(PageRequest.of(nPage,nQuotes));
    }*/
    
    @Override
    public List<Quote> findAll() {
        return quotes.findAll();
    }

    @Override
    public Quote save(Quote quote) {
        return quotes.save(new Quote(quote.getText(),quote.getAuthor(),quote.getBook()));
    }

    @Override
    public Quote editQuote(Quote quote) {
        return quotes.save(quote);
    }

    @Override
    public Quote deleteQuote(Quote quote) {
        quotes.delete(quote);
        return quote;
    }

    public boolean checkValidQuote(Quote quote){

        return quote != null;
    }
}
