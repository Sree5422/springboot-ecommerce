package com.ecommerce.user.service;

import java.awt.font.NumericShaper.Range;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.builder.UserBuilder;
import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserRoleStatusUpdateRequest;
import com.ecommerce.user.dto.request.UserUpdateRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
	}
	
	
	public UserResponse save(UserCreateRequest userCreateRequest) {
		User user= UserBuilder.buildUserFromCreateRequest(userCreateRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 User savedUser = userRepository.save(user);
		UserResponse userResponse = UserBuilder.buildUserResponseFromUser(savedUser);
		return userResponse;
	}
	
	public List<UserResponse> getAllUsers(){
		System.out.println("User Service called");

		return userRepository.findAll()
							  .stream()
							  .map(UserBuilder::buildUserResponseFromUser)
							  .toList();
		
	
	}
	
	public UserResponse updateUserById(long userId, UserUpdateRequest updateRequest) {
		User existingUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with userId"+ userId));
	     User updatedUser = UserBuilder.buildUserFromUserUpdateRequest(existingUser, updateRequest);
	     updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	     User saved = userRepository.save(updatedUser);
	     return UserBuilder.buildUserResponseFromUser(saved);
	     
	}


	public UserResponse getUserById(long userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"+userId));
		
		return UserBuilder.buildUserResponseFromUser(user);
	}


	public String deleteByUserId(long userId) {
		if(!userRepository.existsById(userId)) {
			throw new RuntimeException(("User not found to delete"+ userId));
		}
		userRepository.deleteById(userId);
		return "User Deleted";

		
//		User deleteUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found to delete"+ userId));
//		UserBuilder.buildUserResponseFromUser(deleteUser);
		
	}


	public UserResponse updateRoleStatus(long userId, UserRoleStatusUpdateRequest roleStatusUpdateRequest) {
	 User existingUser = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not present with this Id"));
	 existingUser.setAccountStatus(roleStatusUpdateRequest.getAccountStatus());
	 existingUser.setRole(roleStatusUpdateRequest.getRole());
	 System.out.println(existingUser);
	 User updatedUser = userRepository.save(existingUser);
	 System.out.println(updatedUser);

		return UserBuilder.buildUserResponseFromUser(updatedUser);
	}	
	
	
}
	
	
	
	
	
	
	
//	public UserResponse save(UserCreateRequest userCreateRequest) {
//		
//		User user=UserBuilder.buildUserFromCreateRequest(userCreateRequest);
//		User savedUser= userRepository.save(user);
//		UserResponse userResponse = UserBuilder.buildUserResponseFromUser(savedUser);
//		return userResponse;	
//	}
//	
//	public List<UserResponse> getAllUser(){
//		return userRepository.findAll()
//					   .stream().map(UserBuilder::buildUserResponseFromUser)
//					   .toList();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



