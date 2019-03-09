package com.urjc.daw.practica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class RestSecurityConfiguration {
	
	@Autowired
	AuthenticationProvider authProvider;

	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/books/").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");		
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authProvider);
	}
}
