package com.ecommerce.user.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.user.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	
	public JwtAuthFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader("Authorization");
		System.out.println("Authorization Header = " + header);
		
		if(header==null||!header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		  System.out.println("JWT validation SUCCESS");
		  String token = header.substring(7);
			System.out.println("Token extracted = " + token);

			
		  if(!jwtService.validToken(token)) {
			  System.out.println("JWT validation FAILED");
			  filterChain.doFilter(request, response);
			  return;
		  }
		  System.out.println("JWT validation SUCCESS");
		  
		  Claims claims = jwtService.extractClaims(token);
		  String email = claims.getSubject();
		  String role = claims.get("role", String.class);
		  System.out.println("Email = " + email);
			System.out.println("Role = " + role);

			
			SimpleGrantedAuthority authority= new SimpleGrantedAuthority("ROLE_"+role);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,
																											null,
																											java.util.List.of(authority) 
																										);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
				  
				  System.out.println(
						    "Authenticated = " +
						    SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
						);
				  filterChain.doFilter(request, response);
				  return;
				 
		  
		  
	}
	
	
}
