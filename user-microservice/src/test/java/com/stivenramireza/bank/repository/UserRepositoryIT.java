package com.stivenramireza.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stivenramireza.bank.domain.User;
import com.stivenramireza.bank.domain.UserType;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UserRepositoryIT {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void shouldValidateDependencies() {
		assertNotNull(userRepository);
		assertNotNull(userTypeRepository);
	}
	
	@Test
	@Order(2)
	void shouldCreateUser() {
		// Arrange
		Integer userTypeId = 2;
		String userEmail = "test@gmail.com";
		
		User user = null;
		UserType userType = userTypeRepository.findById(userTypeId).get();
		
		User newUser = new User();
		newUser.setUserEmail(userEmail);
		newUser.setUserType(userType);
		newUser.setName("Test");
		newUser.setEnable("Y");
		newUser.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
		
		// Act
		user = userRepository.save(newUser);
		log.info(user.getName());
		
		// Assert
		assertNotNull(user, "The user is null, could not be saved");
	}
	
	@Test
	@Order(3)
	void shouldUpdateUser() {
		// Arrange
		String userEmail = "test@gmail.com";
		User user = null;
		
		user = userRepository.findById(userEmail).get();
		user.setEnable("N");
		
		// Act
		user = userRepository.save(user);
		
		// Assert
		assertNotNull(user, "The user is null, cannot be updated");
		assertEquals("test@gmail.com", user.getUserEmail());
		assertEquals("N", user.getEnable());
	}
	
	@Test
	@Order(4)
	void shouldDeleteUser() {
		// Arrange
		String userEmail = "test@gmail.com";
		User user = null;
		Optional<User> optionalUser = null;
		
		Boolean existsUser = userRepository.findById(userEmail).isPresent();
		assertTrue(existsUser, "The user was not found");
		
		user = userRepository.findById(userEmail).get();
		
		// Act
		userRepository.delete(user);
		optionalUser = userRepository.findById(userEmail);
		
		// Assert
		assertFalse(optionalUser.isPresent(), "The user could not be deleted");
	}
	
	@Test
	@Order(5)
	void shouldGetAllUsers() {
		// Arrange
		List<User> users = null;
		
		// Act
		users = userRepository.findAll();
		users.forEach(user -> log.info(user.getName()));
		
		// Assert
		assertFalse(users.isEmpty(), "Users do not exist");
	}
	
}
