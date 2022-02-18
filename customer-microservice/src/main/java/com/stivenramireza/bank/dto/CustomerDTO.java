package com.stivenramireza.bank.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String address;
	
	@NotNull
	private Integer custId;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String enable;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String name;
	
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String phone;
	private String token;
	private Integer dotyId_DocumentType;
	
}
