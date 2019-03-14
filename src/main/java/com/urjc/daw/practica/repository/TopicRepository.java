
package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Topic;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    Page<Topic> findAll(Pageable pageable);
    List<Topic> findByQuoteIdsContains(Long id);
    List<Topic> findByNameContains(String keyword);
}
