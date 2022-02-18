package com.stivenramireza.bank.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stivenramireza.bank.domain.Customer;
import com.stivenramireza.bank.dto.CustomerDTO;
import com.stivenramireza.bank.mapper.CustomerMapper;
import com.stivenramireza.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerMapper customerMapper;

	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception {
		List<Customer> foundCustomers = customerService.findAll();
		List<CustomerDTO> customers = customerMapper.listCustomerToListCustomerDTO(foundCustomers);
		return ResponseEntity.ok(customers);
	}

}
