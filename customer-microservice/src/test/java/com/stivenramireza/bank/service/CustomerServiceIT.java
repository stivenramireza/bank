package com.stivenramireza.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stivenramireza.bank.domain.Customer;
import com.stivenramireza.bank.domain.DocumentType;
import com.stivenramireza.bank.repository.DocumentTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class CustomerServiceIT {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	@Test
	@Order(1)
	void shouldValidateDependencies() {
		assertNotNull(customerService);
		assertNotNull(documentTypeRepository);
	}
	
	@Test
	@Order(2)
	void shouldCreateCustomer() throws Exception {
		// Arrange
		Integer documentTypeId = 1;
		Integer customerId = 14836554;
		
		Customer customer = null;
		DocumentType documentType = documentTypeRepository.findById(documentTypeId).get();
		
		Customer newCustomer = new Customer();
		newCustomer.setAddress("Street 48 Avenue 2");
		newCustomer.setCustId(customerId);
		newCustomer.setDocumentType(documentType);
		newCustomer.setEmail("test@gmail.com");
		newCustomer.setEnable("Y");
		newCustomer.setName("Test");
		newCustomer.setPhone("+13214567845");
		newCustomer.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
		
		// Act
		customer = customerService.save(newCustomer);
		log.info(customer.getName());
		
		// Assert
		assertNotNull(customer, "The customer is null, cannot be saved");
	}
	
	@Test
	@Order(3)
	void shouldUpdateCustomer() throws Exception {
		// Arrange
		Integer customerId = 14836554;
		Customer customer = null;
		
		customer = customerService.findById(customerId).get();
		customer.setEnable("N");
		
		// Act
		customer = customerService.update(customer);
		
		// Assert
		assertNotNull(customer, "The customer is null, cannot be updated");
		assertEquals(14836554, customer.getCustId());
		assertEquals("N", customer.getEnable());
		
	}
	
	@Test
	@Order(4)
	void shouldDeleteCustomer() throws Exception {
		// Arrange
		Integer customerId = 14836554;
		Customer customer = null;
		Optional<Customer> optionalCustomer = null;
		
		Boolean existsCustomer = customerService.findById(customerId).isPresent();
		assertTrue(existsCustomer, "The customer was not found");
		
		customer = customerService.findById(customerId).get();
		
		// Act
		customerService.delete(customer);
		optionalCustomer = customerService.findById(customerId);
		
		// Assert
		assertFalse(optionalCustomer.isPresent(), "The customer could not be deleted");
	}
	
	@Test
	@Order(5)
	void shouldGetAllCustomers() throws Exception {
		// Arrange
		List<Customer> customers = null;
		
		// Act
		customers = customerService.findAll();
		customers.forEach(customer -> log.info(customer.getName()));
		
		// Assert
		assertFalse(customers.isEmpty(), "Customers do not exist");
	}

}
