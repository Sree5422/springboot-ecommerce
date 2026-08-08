package com.ecommerce.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {


	private long userId;
	private String userName;
	private String email;
	private String phoneNum;
	private AddressResponse address;
	
}
