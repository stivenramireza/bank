package com.stivenramireza.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	@NotNull
	private Integer custId;
	
	@NotNull
	private Integer dotyId;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String address;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(min = 1,max = 1)
	private String enable;

	@NotNull
	@Size(min = 1,max = 100)
	private String name;

	@NotNull
	@Size(min = 1,max = 100)
	private String phone;
	
	@NotNull
	@Size(min = 1,max = 100)
	private String token;
	
}

