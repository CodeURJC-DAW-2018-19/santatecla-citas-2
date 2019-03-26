package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.CustomUserDetails;
import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository users;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = users.findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new CustomUserDetails(user);
    }
}
