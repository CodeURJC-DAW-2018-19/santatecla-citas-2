package com.urjc.daw.practica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.headers().frameOptions().disable();
		http.requiresChannel().antMatchers("/*").requiresSecure();

		//http.authorizeRequests().antMatchers("/quote", "/topicForm", "createTopic", "editTopic")
		//		.hasAuthority("ROLE_ADMIN");

		// Web resources
		http.authorizeRequests().antMatchers("/static/**").permitAll();

		// Login form
		http.formLogin().loginPage("/login").permitAll();
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/logout");
	}



	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(this.authProvider);
	}
}
