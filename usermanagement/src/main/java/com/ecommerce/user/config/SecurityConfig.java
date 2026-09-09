package com.ecommerce.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.user.security.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;
	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//1st
//		httpSecurity
//	    .csrf(csrf -> csrf.disable())
//	    .authorizeHttpRequests(auth -> auth
//	        .anyRequest().permitAll());

		// The 2nd allow only JWT
		httpSecurity.csrf(csrf->csrf.disable())
					.authorizeHttpRequests(auth-> auth.
							
													requestMatchers("/users/auth/login").permitAll()
													.requestMatchers(HttpMethod.POST,"/users").permitAll()
													.anyRequest().authenticated()
													).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
		
		//The 3rd RBAC
		//.requestMatchers("/users/**").hasRole("ADMIN")

	
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
