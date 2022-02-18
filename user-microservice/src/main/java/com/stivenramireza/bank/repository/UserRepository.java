package com.stivenramireza.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stivenramireza.bank.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
