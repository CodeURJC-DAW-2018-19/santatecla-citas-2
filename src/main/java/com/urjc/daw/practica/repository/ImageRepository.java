package com.urjc.daw.practica.repository;


import com.urjc.daw.practica.model.QuoteImage;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<QuoteImage,Long> {
}
