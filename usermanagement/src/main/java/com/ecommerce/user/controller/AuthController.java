package com.ecommerce.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.request.AuthLoginRequest;
import com.ecommerce.user.dto.response.AuthLoginResponse;
import com.ecommerce.user.service.AuthService;

import jakarta.validation.Valid;



@RestController
@RequestMapping ("/users/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	
	public AuthLoginResponse loginUser(@Valid @RequestBody  AuthLoginRequest authLoginRequest) {
		return authService.loginUser(authLoginRequest);
	}
}
