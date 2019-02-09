package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends CrudRepository<Theme,Long> {
}
