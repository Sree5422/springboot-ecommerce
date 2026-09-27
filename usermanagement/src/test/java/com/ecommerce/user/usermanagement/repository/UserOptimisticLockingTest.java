package com.ecommerce.user.usermanagement.repository;

	import static org.junit.jupiter.api.Assertions.assertThrows;

	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

	import com.ecommerce.user.enums.AccountStatus;
	import com.ecommerce.user.enums.Role;
	import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

	@SpringBootTest
	@Transactional
	@Rollback
	public class UserOptimisticLockingTest {


	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private EntityManager entityManager;

	    @Test
	    void shouldThrowExceptionWhenUpdatingStaleUser() {

	        // 1. Create test user
	        User user = User.builder()
	                .userName("optimistic_test_userr")
	                .email("optimistic_test@gmail.com")
	                .password("Test@12345")
	                .phoneNum("9876543210")
	                .role(Role.CUSTOMER)
	                .accountStatus(AccountStatus.ACTIVE)
	                .build();


	        User savedUser = userRepository.saveAndFlush(user);

	        Long userId = savedUser.getUserId();

	        System.out.println("Created test user ID: " + userId);
	        System.out.println("Initial version: " + savedUser.getVersion());

	        // 2. Load the same user
	        User user1 = userRepository.findById(userId)
	                .orElseThrow();

	        // Clear first persistence context
	        entityManager.clear();

	        // 3. Load the same user again
	        User user2 = userRepository.findById(userId)
	                .orElseThrow();

	        System.out.println("User1 version: " + user1.getVersion());
	        System.out.println("User2 version: " + user2.getVersion());

	        // Both objects have the same version
	        user1.setUserName("Updated_By_User1");

	        // 4. First update succeeds
	        userRepository.saveAndFlush(user1);

	        System.out.println("User1 updated successfully");
	        System.out.println("User1 new version: " + user1.getVersion());

	        // 5. Second object is now stale
	        user2.setUserName("Updated_By_User2");

	        // 6. Second update should fail
	        assertThrows(
	                ObjectOptimisticLockingFailureException.class,
	                () -> userRepository.saveAndFlush(user2)
	        );

	       System.out.println("Optimistic locking conflict detected!");
	    }
	}
	
	
