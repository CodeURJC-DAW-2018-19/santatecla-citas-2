package com.urjc.daw.practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired(required=true)
    AuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	http.authorizeRequests().antMatchers("/","/login", "/error").permitAll();

    }

    protected void configure (AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }
}
