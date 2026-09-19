package com.ecommerce.user.builder;

import com.ecommerce.user.dto.request.AddressCreateRequest;
import com.ecommerce.user.dto.request.AddressUpdateRequest;
import com.ecommerce.user.dto.request.MyProfileUpdateRequest;
import com.ecommerce.user.dto.request.UserCreateRequest;
import com.ecommerce.user.dto.request.UserUpdateRequest;
import com.ecommerce.user.dto.response.AddressResponse;
import com.ecommerce.user.dto.response.AuthLoginResponse;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.enums.Role;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;

public class UserBuilder {
	
	public static User buildUserFromCreateRequest(UserCreateRequest userCreateRequest) {
				
				return User.builder()
				.userName(userCreateRequest.getUserName())
				.email(userCreateRequest.getEmail())
				.password(userCreateRequest.getPassword())
				.phoneNum(userCreateRequest.getPhoneNum())
				.role(Role.CUSTOMER)
				.accountStatus(AccountStatus.ACTIVE)
				.address(buildAddressFromAddressCreateRequest(userCreateRequest.getAddress()))
				.build();
	}
	


	public static User buildUserFromUserUpdateRequest(User existingUser,UserUpdateRequest userUpdateRequest) {  
		return User.builder()            
				.userId(existingUser.getUserId())      
			//	.password(userUpdateRequest.getPassword())        
				.password(userUpdateRequest.getPassword() != null? userUpdateRequest.getPassword(): existingUser.getPassword())
				.userName(userUpdateRequest.getUserName() != null? userUpdateRequest.getUserName() : existingUser.getUserName())   
				.email(userUpdateRequest.getEmail() != null? userUpdateRequest.getEmail(): existingUser.getEmail())     
				.phoneNum(userUpdateRequest.getPhoneNum() != null? userUpdateRequest.getPhoneNum() : existingUser.getPhoneNum())   
				.role(existingUser.getRole())
				.accountStatus(existingUser.getAccountStatus())
				.address(buildAddressFromAddressUpdateRequest( existingUser.getAddress(),userUpdateRequest.getAddress())) 
				
				.build();
		}
	 
    private static Address buildAddressFromAddressUpdateRequest(Address existingAddress, AddressUpdateRequest addressUpdateRequest) {
		// TODO Auto-generated method stub
		return Address.builder()
				.addressId(existingAddress.getAddressId())
				.city(addressUpdateRequest.getCity())
				.country(addressUpdateRequest.getCountry())
				.state(addressUpdateRequest.getState())
				.street(addressUpdateRequest.getStreet())
				.pincode(addressUpdateRequest.getPincode())
				.build();
	}

	public static Address buildAddressFromAddressCreateRequest(AddressCreateRequest addressCreateRequest) {
		// TODO Auto-generated method stub
			return Address.builder()
					.city(addressCreateRequest.getCity())
					.state(addressCreateRequest.getState())
					.street(addressCreateRequest.getStreet())
					.pincode(addressCreateRequest.getPincode())
					.country(addressCreateRequest.getCountry())
					.build();
					
					
					
			
	}

	public static UserResponse buildUserResponseFromUser(User user) {
		// TODO Auto-generated method stub
		
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.phoneNum(user.getPhoneNum())
				.role(user.getRole())
				.accountStatus(user.getAccountStatus())
		
				.address(buildAddressResponseFromAddress(user.getAddress()))
						.build();
	}
	
	public static AddressResponse buildAddressResponseFromAddress(Address address) {
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.city(address.getCity())
				.country(address.getCountry())
				.state(address.getState())
				.street(address.getStreet())
				.pincode(address.getPincode())
				.build();
	}
	
	//public static Us buildLoginReq

	public static AuthLoginResponse buildAuthUserResponseFromUser(User user,String token) {
		// TODO Auto-generated method stub
		
		return AuthLoginResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.role(user.getRole())
				.accountStatus(user.getAccountStatus())
				.token(token)
								.build();
	}



	public static User buildUserFromMyProfileUpdateRequest( User user, MyProfileUpdateRequest request) {

	    user.setUserName(request.getUserName());
	    user.setEmail(request.getEmail());
	    user.setPhoneNum(request.getPhoneNum());

	    if (request.getAddress() != null) {
	        user.setAddress(
	            buildAddressFromAddressUpdateRequest(
	                user.getAddress(),
	                request.getAddress()
	            )
	        );
	    }

	    return user;
	}
}
