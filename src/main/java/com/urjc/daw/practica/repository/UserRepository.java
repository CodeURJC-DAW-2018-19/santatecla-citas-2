package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByName(String name);
}
