package com.ecommerce.user.dto.request;

import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleStatusUpdateRequest {

	@NotNull(message = "Role is required")
	private Role role;
	
	@NotNull(message="Status should not be empty")
	private AccountStatus accountStatus;
}
