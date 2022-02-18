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

import com.stivenramireza.bank.domain.Transaction;
import com.stivenramireza.bank.domain.User;
import com.stivenramireza.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional	(readOnly = true)
	public Long count() {
		return userRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User user) throws Exception {
		if (user == null) {
			throw new Exception("The user is null");
		}
		
		validate(user);
		
		Boolean existsUser = userRepository.existsById(user.getUserEmail());
		if (existsUser) {
			throw new Exception("The user already exists");
		}
		
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User update(User user) throws Exception {
		if (user == null) {
			throw new Exception("The user is null");
		}
		
		validate(user);
		
		Boolean existsUser = userRepository.existsById(user.getUserEmail());
		if(!existsUser) {
			throw new Exception("The user does not exist");
		}
		
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(User user) throws Exception {
		if (user == null) {
			throw new Exception("The user is null");
		}
		
		String userEmail = user.getUserEmail();
		if (userEmail == null) {
			throw new Exception("The user email is null");
		}
		
		Boolean existsUser = userRepository.existsById(userEmail);
		if (!existsUser) {
			throw new Exception("The user does not exist");
		}
		
		findById(userEmail).ifPresent(t -> {
			List<Transaction> transactions = t.getTransactions();
			if(transactions != null && !transactions.isEmpty()) {
				throw new RuntimeException("The user has transactions");
			}
		});
		
		userRepository.deleteById(userEmail);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null) {
			throw new Exception("The id is null");
		}
		
		Boolean existsUser = userRepository.existsById(id);
		if(!existsUser) {
			throw new Exception("The user does not exist");
		}
		
		User user = userRepository.findById(id).get();
		delete(user);
	}

	@Override
	public void validate(User user) throws Exception {
		Set<ConstraintViolation<User>> constraintViolation = validator.validate(user);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

}
