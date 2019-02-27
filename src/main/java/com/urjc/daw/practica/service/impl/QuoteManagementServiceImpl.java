package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.awt.print.Pageable;
import java.util.ArrayList;
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
    public List<Quote> findAll(int nPag, int quotesPerPage) {
        List<Quote> list =   quotes.findAll(PageRequest.of(nPag,quotesPerPage)).getContent();
        return list;
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
        List<Quote> quotesFound = new ArrayList<>();
        if(!CollectionUtils.containsAny(quotesFound,findByAuthor(keyword))){
            quotesFound.addAll(findByAuthor(keyword));
        }
        if(!CollectionUtils.containsAny(quotesFound,findByBook(keyword))){
            quotesFound.addAll(findByBook(keyword));
        }
        if(!CollectionUtils.containsAny(quotesFound,quotes.findByTextContaining(keyword))){
            quotesFound.addAll(quotes.findByTextContaining(keyword));
        }

        return quotesFound;
    }

    @Override
    public List<Quote> findByAuthor(String author) {
        return quotes.findByAuthorContaining(author);
    }

    @Override
    public List<Quote> findByBook(String book) {
        return quotes.findByBookContaining(book);
    }

    public boolean checkValidQuote(Quote quote){

        return quote != null;
    }
}
