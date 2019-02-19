package com.urjc.daw.practica;

import com.urjc.daw.practica.model.User;
import com.urjc.daw.practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserAuthProvider implements AuthenticationProvider {

    @Autowired
    UserRepository users;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = users.findByName(authentication.getName());
        if(user == null){
            throw new BadCredentialsException("User not found");
        }
        String password = (String) authentication.getCredentials();
        if(!new BCryptPasswordEncoder().matches(password,user.getPassword())){
            throw new BadCredentialsException("User not found");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        for(String role:user.getRoles()){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(user.getName(),password,roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}