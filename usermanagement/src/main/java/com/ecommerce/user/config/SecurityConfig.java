package com.ecommerce.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.user.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}
	@Bean
	
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity
//	    .csrf(csrf -> csrf.disable())
//	    .authorizeHttpRequests(auth -> auth
//	        .anyRequest().permitAll());

		httpSecurity.csrf(csrf->csrf.disable())
					.authorizeHttpRequests(auth-> auth.
							
													requestMatchers("/users/auth/login").permitAll()
													.anyRequest().authenticated()
													).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
