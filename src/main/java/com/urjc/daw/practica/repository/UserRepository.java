package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
