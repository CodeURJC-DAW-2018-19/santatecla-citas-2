package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Text;
import com.urjc.daw.practica.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends CrudRepository<Text,Long> {

    List<Text> findAll();
    List<Text> findByTopic(Topic topic);
}
