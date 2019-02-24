package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteManagementServiceImpl implements QuoteManagementService {

    @Autowired
    QuoteRepository quotes;

    @Autowired
    TopicManagementService topicService;

    @Override
    public Optional<Quote> findOne(Long id) {
        return quotes.findById(id);
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
        //return quotes.save(new Quote(quote.getText(),quote.getAuthor(),quote.getBook()));
    	return quotes.save(quote);
    }

    @Override
    public Quote editQuote(Quote quote) {
        return quotes.save(quote);
    }

    @Override
    public Quote deleteQuote(Long id) {
        Quote quote = quotes.findById(id).get();
        quotes.delete(quote);
        topicService.deleteReference(quote.getId());
        return quote;
    }

    @Override
    public List<Quote> findByIdDiferrentThan(List<Long> ids) {
        return quotes.findByIdNotIn(ids);
    }

    @Override
    public List<Quote> findByKeyword(String keyword) {
        return quotes.findByTextContaining(keyword);
    }

    public boolean checkValidQuote(Quote quote){

        return quote != null;
    }
}
