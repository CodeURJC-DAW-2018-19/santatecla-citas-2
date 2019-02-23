package com.urjc.daw.practica.security;

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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthProvider implements AuthenticationProvider {

    @Autowired
    UserComponent users;
    
    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
    	User user = userRepo.findByName(authentication.getName());
        
        if(user == null){
            throw new BadCredentialsException("User not found");
        }
        
        String password = (String) authentication.getCredentials();
        System.out.println("Entra en auto");
        
        if(!new BCryptPasswordEncoder().matches(password,user.getPassword())){
            throw new BadCredentialsException("Password failed");
        }else {
        	
        	users.setLoggedUser(user);        	
	        List<GrantedAuthority> roles = new ArrayList<>();
	        for(String role:user.getRoles()){
	            roles.add(new SimpleGrantedAuthority(role));
	        }
        
	        return new UsernamePasswordAuthenticationToken(user.getName(),password,roles);
        }
    }

    public BCryptPasswordEncoder getEncoder(){
        return encoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
