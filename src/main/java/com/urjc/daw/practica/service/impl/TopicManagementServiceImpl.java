package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.repository.TopicRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicManagementServiceImpl implements TopicManagementService {
    
	@Autowired
    TopicRepository topics;

	@Autowired
    QuoteManagementService quoteService;


    @Override
    public Topic save(Topic topic) {
        topics.save(topic);
        return null;
    }

    @Override
    public List<Topic> findAll() {
        return topics.findAll();
    }

    @Override
    public Optional<Topic> findOne(Long id) {
        return topics.findById(id);
    }

    @Override
    public List<Quote> getReferencedQuotes(Topic currentTopic) {
        List<Quote> quotesReferenced = new ArrayList<>();

        for (Long quoteId:currentTopic.getQuoteIds()){
            Optional<Quote> currentQuote = quoteService.findOne(quoteId);
            if(currentQuote.isPresent()) {
                quotesReferenced.add(currentQuote.get());
            }
        }
        return quotesReferenced;

    }

    @Override
    public void deleteReference(Long id) {
        List<Topic> updated = topics.findByQuoteIdsContains(id);

        for(Topic topic:updated){
            topic.getQuoteIds().remove(id);
        }

    }

    @Override
    public void deleteTopic(Topic topic) {
        topics.delete(topic);
    }

    @Override
    public List<Topic> findByKeyword(String keyword) {
        return topics.findByNameContains(keyword);
    }
}
