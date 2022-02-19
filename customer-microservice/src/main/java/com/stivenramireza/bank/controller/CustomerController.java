package com.stivenramireza.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public CustomerDTO findById(@PathVariable("id") Integer id) throws Exception {
		Boolean existsCustomer = customerService.findById(id).isPresent();
		Customer customer = existsCustomer ? customerService.findById(id).get() : null;
		return customerMapper.customerToCustomerDTO(customer);
	}

	@GetMapping
	public List<CustomerDTO> findAll() throws Exception {
		List<Customer> customers = customerService.findAll();
		List<CustomerDTO> customerDTOs = customerMapper.customerListToCustomerDTOList(customers);
		return customerDTOs;
	}
	
	@PostMapping
	public CustomerDTO save(@RequestBody CustomerDTO customerDTO) throws Exception {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer = customerService.save(customer);
		customerDTO = customerMapper.customerToCustomerDTO(customer);
		return customerDTO;
	}

}
