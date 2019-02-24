package com.urjc.daw.practica;


import com.urjc.daw.practica.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.headers().frameOptions().disable();

		// Web resources
		http.authorizeRequests().antMatchers("/static/**").permitAll();


		// Login form
		http.formLogin().loginPage("/login").loginProcessingUrl("/login");
		http.formLogin().usernameParameter("user");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/topicForm").hasRole("ADMIN").antMatchers("/quoteForm").hasRole("USER")
			//	.antMatchers("/", "main").permitAll().anyRequest().authenticated().and().httpBasic();

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/logout");

	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider
				= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(getEncoder());
		return authProvider;
	}
	@Bean
	public BCryptPasswordEncoder getEncoder(){

		return new BCryptPasswordEncoder();
	}


	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
}
