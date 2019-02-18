package com.urjc.daw.practica;

import com.urjc.daw.practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthProvider implements AuthebticationProvider {

    @Autowired
    UserRepository users;


}
