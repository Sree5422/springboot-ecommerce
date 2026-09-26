package com.ecommerce.user.service;

import com.ecommerce.user.security.JwtAuthFilter;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.user.builder.UserBuilder;
import com.ecommerce.user.dto.request.ChangePasswordRequest;
import com.ecommerce.user.dto.request.MyProfileUpdateRequest;
import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserRoleStatusUpdateRequest;
import com.ecommerce.user.dto.request.UserUpdateRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.exceptions.InvalidUserException;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

@Service
public class UserService {
	
	private final JwtAuthFilter jwtAuthFilter;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder, JwtAuthFilter jwtAuthFilter) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	
	public UserResponse save(UserCreateRequest userCreateRequest) {
		User user= UserBuilder.buildUserFromCreateRequest(userCreateRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 User savedUser = userRepository.save(user);
		UserResponse userResponse = UserBuilder.buildUserResponseFromUser(savedUser);
		return userResponse;
	}
	
	public Page<UserResponse> getAllUsers(Pageable pageable){
		System.out.println("Pageable User Service called");

		return userRepository.findAll(pageable)
							  
							  .map(UserBuilder::buildUserResponseFromUser);
							  
	}
	
//	public List<UserResponse> getAllUsers(){
//		System.out.println("User Service called");
//
//		return userRepository.findAll()
//							  .stream()
//							  .map(UserBuilder::buildUserResponseFromUser)
//							  .toList();
//	}

	
	@Transactional(propagation  = Propagation.REQUIRED)
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


	public UserResponse getMyProfile() {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		String email = authentication.getName();
		System.out.println(email);
		System.out.println(authentication.getAuthorities());
		  System.out.println( authentication.getPrincipal());
		    User userByEmail = userRepository.findUserByEmail(email);
		    									//.orElseThrow(()->new UserNotFoundException("User not found with this email: "+ email));
		   
				  							
		return UserBuilder.buildUserResponseFromUser(userByEmail);
	}


	
	public UserResponse updateProfile(MyProfileUpdateRequest request) {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    String email = authentication.getName();

	     User user = userRepository.findUserByEmail(email);
	            
	     
	    User updatedUser = UserBuilder.buildUserFromMyProfileUpdateRequest(user, request);

	    User savedUser = userRepository.save(updatedUser);

	    return UserBuilder.buildUserResponseFromUser(savedUser);
	}


	public void updatePassword(@Valid ChangePasswordRequest changePasswordRequest) {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String namemail = authentication.getName();
		System.out.println(namemail);
		User user = userRepository.findUserByEmail(namemail);
		if (user == null) {
		    throw new UserNotFoundException("User not found");
		}
		
	    if (!passwordEncoder.matches(
	           changePasswordRequest.getCurrentPassword(),
	            user.getPassword())) {
	    	

	        throw new InvalidUserException("Current password is incorrect");
	    }
	    user.setPassword(passwordEncoder.encode(changePasswordRequest.getUpdatedPassword()));
	    userRepository.save(user);		
	}


	public void deactivate() {
		@Nullable
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		User userByEmail = userRepository.findUserByEmail(name);

	    if (userByEmail == null) {
	        throw new UserNotFoundException("User not found");
	    }
		userByEmail.setAccountStatus(AccountStatus.INACTIVE);
		userRepository.save(userByEmail);
		// TODO Auto-generated method stub
		
	}
	
}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



