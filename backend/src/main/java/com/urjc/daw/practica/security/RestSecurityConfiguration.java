package com.urjc.daw.practica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/topics").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logout").permitAll();


		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/quotes/").hasAnyRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/quotes/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/quotes/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/quotes/**").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/topics/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/topics/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/topics/**").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/graph").hasAnyRole("USER");

		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.formLogin().loginPage("/api/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/api/quotes")
				.failureUrl("/api/error");

		http.logout().logoutUrl("/api/logout").logoutSuccessUrl("/api/topics");

		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authProvider);
	}
}
