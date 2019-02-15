package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Long> {

    List<Quote> findAll();
    Quote findQuoteById(Long id);
    List<Quote> findByAuthor(String author);
}
