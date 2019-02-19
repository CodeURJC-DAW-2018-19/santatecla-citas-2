package com.urjc.daw.practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthProvider authProvider;

    protected void configure(HttpSecurity security) throws Exception{

    }

    protected void configure (AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }
}
