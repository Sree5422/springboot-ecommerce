package com.ecommerce.user.model;

import com.ecommerce.user.enums.AccountStatus;
import com.ecommerce.user.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(unique=true)
    private String userName;
    @Column(unique=true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique=true)

    private String phoneNum;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    
    private Address address;

	public User(String userName, String email, String password, String phoneNum, Address address) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNum = phoneNum;
		this.address = address;
	}

	public User(String userName, String email, String password, String phoneNum) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNum = phoneNum;
	}
	
    
	
}