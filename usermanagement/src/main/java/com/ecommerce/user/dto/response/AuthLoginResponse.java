package com.ecommerce.user.dto.response;

import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
	
	private long userId;
	private String userName;
	private String email;
	private Role role;
	private AccountStatus accountStatus;

}
