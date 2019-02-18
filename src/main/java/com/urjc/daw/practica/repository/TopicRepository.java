<<<<<<< HEAD:src/main/java/com/urjc/daw/practica/repository/TopicRepository.java
package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends CrudRepository<Theme,Long> {
}
=======
package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Long> {

    Topic findTopicById(Long id);
}
>>>>>>> fd5012c0c418b2cb06b0a07088eb28ef975c7f2a:src/main/java/com/urjc/daw/practica/repository/TopicRepository.java
