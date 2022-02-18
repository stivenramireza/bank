package com.stivenramireza.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stivenramireza.bank.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
