package com.ecommerce.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.dto.response.AuthLoginResponse;
import com.ecommerce.user.exceptions.UserNotFoundException;
import com.ecommerce.user.model.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long>{

	User findUserByEmail(String email);
	
	@EntityGraph(attributePaths = "address")
//	List<User> findAllUsersWithAddress();
	Page<User> findAllBy(Pageable pageable);
}
