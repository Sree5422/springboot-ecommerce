package com.ecommerce.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserUpdateRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
		
	}
	
	@PostMapping
	
	public UserResponse saveUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
		return userService.save(userCreateRequest);
	}

	@GetMapping
	
	public List<UserResponse> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	public UserResponse getUserById(@PathVariable long userId) {
		return userService.getUserById(userId);
	}
	
	@PatchMapping("/{userId}")
	
	public UserResponse updateUserById(@PathVariable long userId, @RequestBody @Valid UserUpdateRequest updateRequest) {
		return userService.updateUserById(userId, updateRequest);
	}
	@DeleteMapping("/{userId}")
	public String deleteByUserId(@PathVariable long userId) {
		return userService.deleteByUserId(userId);
	}
}
