package com.ecommerce.user.dto.request;

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
public class UserCreateRequest {
	
	@NotBlank(message = "User Name is required")
	@Size(min = 3, max = 50, message = "username length should be within 3 - 50")

	private String userName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter valid Email")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 8,message = "password minimum length is 8 ")
	private String password;
	

	@NotBlank(message = "phone number is required" )
	@Pattern(regexp = "^[6-9][0-9]{9}$", message="Phone number must contain exactly 10 digits")

	private String phoneNum;
	
	@Valid
	private AddressCreateRequest address;
	
	
	
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getPhoneNum() {
//		return phoneNum;
//	}
//	public void setPhoneNum(String phoneNum) {
//		this.phoneNum = phoneNum;
//	}
//	public AddressCreateRequest getAddress() {
//		return address;
//	}
//	public void setAddress(AddressCreateRequest address) {
//		this.address = address;
//	}
	
	
	

}
