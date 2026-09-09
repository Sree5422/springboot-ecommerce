package com.ecommerce.user.dto.response;

import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.enums.Role;
import com.ecommerce.user.model.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
	
	private long userId;
	private String userName;
	private String email;
	private Role role;
	private AccountStatus accountStatus;
	private String token;

}
