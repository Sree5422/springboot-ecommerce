package com.ecommerce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.dto.response.AuthLoginResponse;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.model.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByEmail(String email);
}
