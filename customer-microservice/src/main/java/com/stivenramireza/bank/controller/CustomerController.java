package com.stivenramireza.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stivenramireza.bank.domain.Customer;
import com.stivenramireza.bank.dto.CustomerDTO;
import com.stivenramireza.bank.mapper.CustomerMapper;
import com.stivenramireza.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;

	@GetMapping
	public List<CustomerDTO> findAll() throws Exception {
		List<Customer> customers = customerService.findAll();
		List<CustomerDTO> customerDTOs = customerMapper.customerListToCustomerDTOList(customers);
		return customerDTOs;
	}

}
