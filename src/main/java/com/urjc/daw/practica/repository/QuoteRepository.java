package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Long> {

    Page<Quote> findAll(Pageable pageable);
    Quote findQuoteById(Long id);
    Page<Quote> findByAuthor(String author, Pageable pageable);

}
