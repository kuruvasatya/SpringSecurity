package com.app.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
        	.userDnPatterns("uid={0},ou=people") //Dn=> Distinguish Name
        	.groupSearchBase("ou=groups")
        	.contextSource()
        	.url("ldap://localhost:8399/dc=springframework,dc=org")
        	.and()
        	.passwordCompare()
        	.passwordEncoder(new BCryptPasswordEncoder())
        	.passwordAttribute("userPassword");
	}	
}
