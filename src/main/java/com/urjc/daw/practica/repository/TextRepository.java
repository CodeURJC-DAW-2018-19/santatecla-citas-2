package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Text;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends CrudRepository<Text,Long> {
}
