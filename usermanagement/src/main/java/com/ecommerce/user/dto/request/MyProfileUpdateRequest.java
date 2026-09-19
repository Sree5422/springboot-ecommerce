package com.ecommerce.user.dto.request;

import com.ecommerce.user.model.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyProfileUpdateRequest {
	
	@NotBlank(message = "username is required")
	@Size(min=3, max=50, message = "user name length should be with in 3 - 50")
	private String userName;
	
	@NotBlank(message = "email is required")
	@Email(message = "enter valid email")
	private String email;
	
	@NotBlank(message = "phone number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "enter valid phone number")
	private String phoneNum;
	
	@Valid
	private AddressUpdateRequest address;
	
	

}
