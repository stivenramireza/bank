package com.stivenramireza.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.stivenramireza.bank.domain.Customer;
import com.stivenramireza.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
	
	@Mapping(source = "documentType.dotyId", target = "dotyId_DocumentType")
	public CustomerDTO customerToCustomerDTO(Customer customer);

	@Mapping(source = "dotyId_DocumentType", target = "documentType.dotyId")
	public Customer customerDTOToCustomer(CustomerDTO customerDTO);

	public List<CustomerDTO> listCustomerToListCustomerDTO(List<Customer> customers);

	public List<Customer> listCustomerDTOToListCustomer(List<CustomerDTO> customerDTOs);

}
