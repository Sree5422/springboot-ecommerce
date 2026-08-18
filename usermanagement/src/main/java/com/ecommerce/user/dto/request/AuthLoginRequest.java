package com.ecommerce.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest {


	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid Email")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 8,message = "password minimum length is 8 ")
	private String password;
}
