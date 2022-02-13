package com.stivenramireza.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stivenramireza.bank.domain.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
