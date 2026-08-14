package com.ecommerce.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.user.builder.UserBuilder;
import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserUpdateRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	public UserResponse save(UserCreateRequest userCreateRequest) {
		User user= UserBuilder.buildUserFromCreateRequest(userCreateRequest);
		 User savedUser = userRepository.save(user);
		UserResponse userResponse = UserBuilder.buildUserResponseFromUser(savedUser);
		return userResponse;
	}
	
	public List<UserResponse> getAllUsers(){
		return userRepository.findAll()
							  .stream()
							  .map(UserBuilder::buildUserResponseFromUser)
							  .toList();
		
	
	}
	
	public UserResponse updateUserById(long userId, UserUpdateRequest updateRequest) {
		User existingUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with userId"+ userId));
	     User userFromUserUpdateRequest = UserBuilder.buildUserFromUserUpdateRequest(existingUser, updateRequest);
	     User saved = userRepository.save(userFromUserUpdateRequest);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



