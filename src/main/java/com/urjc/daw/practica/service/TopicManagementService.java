package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TopicManagementService {

    Topic save(Topic topic);
    
    Page<Topic> findAll(int nPag,int topicsPerPage);
    Optional<Topic> findOne(Long id);
    List<Quote> getReferencedQuotes(Topic currentTopic);
    void deleteReference(Long id);
    void deleteTopic(Topic topic);
    List<Topic> findByKeyword(String keyword);
}
