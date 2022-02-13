package com.stivenramireza.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stivenramireza.bank.domain.Account;
import com.stivenramireza.bank.domain.Customer;
import com.stivenramireza.bank.domain.RegisteredAccount;
import com.stivenramireza.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}
	
	@Override
	@Transactional	(readOnly = true)
	public Long count() {
		return customerRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer save(Customer customer) throws Exception {
		if (customer == null) {
			throw new Exception("The customer is null");
		}
		
		validate(customer);
		
		Boolean existsCustomer = customerRepository.existsById(customer.getCustId());
		if (existsCustomer) {
			throw new Exception("The customer already exists");
		}
		
		return customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer update(Customer customer) throws Exception {
		if (customer == null) {
			throw new Exception("The customer is null");
		}
		
		validate(customer);
		
		Boolean existsCustomer = customerRepository.existsById(customer.getCustId());
		if(!existsCustomer) {
			throw new Exception("The customer does not exist");
		}
		
		return customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Customer customer) throws Exception {
		if (customer == null) {
			throw new Exception("The customer is null");
		}
		
		Integer customerId = customer.getCustId();
		if (customerId == null) {
			throw new Exception("The customer id is null");
		}
		
		Boolean existsCustomer = customerRepository.existsById(customerId);
		if (!existsCustomer) {
			throw new Exception("The customer does not exist");
		}
		
		findById(customerId).ifPresent(c -> {
			List<Account> accounts = c.getAccounts();
			if(accounts != null && !accounts.isEmpty()) {
				throw new RuntimeException("The customer has related accounts");
			}
			
			List<RegisteredAccount> registeredAccounts = c.getRegisteredAccounts();
			if(registeredAccounts != null && !registeredAccounts.isEmpty()) {
				throw new RuntimeException("The customer has registered accounts");
			}
		});
		
		customerRepository.deleteById(customerId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("The id is null");
		}
		
		Boolean existsCustomer = customerRepository.existsById(id);
		if(!existsCustomer) {
			throw new Exception("The customer does not exist");
		}
		
		Customer customer = customerRepository.findById(id).get();
		delete(customer);
	}

	@Override
	public void validate(Customer customer) throws Exception {
		Set<ConstraintViolation<Customer>> constraintViolation = validator.validate(customer);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

}
