
package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Long> {

    List<Topic> findAll();
    List<Topic> findByQuoteIdsContains(Long id);

    List<Topic> findByNameContains(String keyword);
}
