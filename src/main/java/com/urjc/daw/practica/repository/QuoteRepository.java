package com.urjc.daw.practica.repository;

import com.sun.org.apache.xpath.internal.operations.Quo;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {

    /*Page<Quote> findAll(Pageable pageable);
    List<Quote> findAll();
    Optional<Quote> findQuoteById(Long id);*/
    Page<Quote> findByAuthor(String author, Pageable pageable);

}
