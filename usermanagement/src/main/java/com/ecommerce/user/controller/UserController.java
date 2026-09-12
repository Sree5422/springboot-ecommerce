package com.ecommerce.user.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserRoleStatusUpdateRequest;
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
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserResponse> getUsers() {
		System.out.println("User called");
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse getUserById(@PathVariable long userId) {
		return userService.getUserById(userId);
	}
	
	@PatchMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse updateUserById(@PathVariable long userId, @RequestBody @Valid UserUpdateRequest updateRequest) {
		return userService.updateUserById(userId, updateRequest);
	}
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteByUserId(@PathVariable long userId) {
		return userService.deleteByUserId(userId);
	}

	@PatchMapping("/{userId}/role-status")
	@PreAuthorize("hasRole('ADMIN')")
	public UserResponse updateRoleStatus(@PathVariable long userId, @RequestBody @Valid UserRoleStatusUpdateRequest roleStatusUpdateRequest) {
		return userService.updateRoleStatus(userId,roleStatusUpdateRequest);
	}
}
