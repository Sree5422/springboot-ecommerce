package com.ecommerce.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private String street;
	private String city;
	private String pincode;
	private String state;
	private String country;
	
	public Address(String street, String city, String pincode, String state, String country) {
		super();
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
	}


	
	

	
	

}
