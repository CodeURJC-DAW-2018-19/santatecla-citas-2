package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Long> {


}
