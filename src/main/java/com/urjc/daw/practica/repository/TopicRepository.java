
package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Long> {

    Topic findTopicById(Long id);
}
