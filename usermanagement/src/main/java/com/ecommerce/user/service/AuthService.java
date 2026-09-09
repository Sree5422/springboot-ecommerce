package com.ecommerce.user.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.builder.UserBuilder;
import com.ecommerce.user.dto.request.AuthLoginRequest;
import com.ecommerce.user.dto.response.AuthLoginResponse;
import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.exceptions.AccountNotActiveException;
import com.ecommerce.user.exceptions.InvalidCredentialsException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthService(UserRepository repository,PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public AuthLoginResponse loginUser(AuthLoginRequest authLoginRequest) { //newupdatedDetails
		String loginEmail = authLoginRequest.getEmail();
		User userByEmail = repository.findUserByEmail(loginEmail); //oldDBdetails
		
		System.out.println("User found: " + (userByEmail != null));

		if (userByEmail != null) {
		    System.out.println("Email: " + userByEmail.getEmail());
		    System.out.println("Status: " + userByEmail.getAccountStatus());
		}
	  //  System.out.println("Email received: " + loginEmail);
	 //   System.out.println("User found: " + (userByEmail != null));

		if(userByEmail==null)
			throw new InvalidCredentialsException("Invalid Email or password");
		
		boolean matches = passwordEncoder.matches(authLoginRequest.getPassword(),userByEmail.getPassword()); //rawpw,oldHashpw
		
		//  System.out.println("Password matches: " + matches);
		  //  System.out.println("Account status: " + userByEmail.getAccountStatus());
		    
		if(!matches) throw new InvalidCredentialsException("Invalid Email or password");
		
		if(AccountStatus.ACTIVE != userByEmail.getAccountStatus())
		    throw new AccountNotActiveException("User is Not Active");
		
		String token = jwtService.generateToken(userByEmail);
		System.out.println(token);
		AuthLoginResponse authLoginResponse = UserBuilder.buildAuthUserResponseFromUser(userByEmail,token);
	
		return authLoginResponse;		
		
	}

	
	
}
