package com.stivenramireza.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balances")
public class BalanceController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/{id}")
	public BalanceDTO findBalance(@PathVariable("id") String id) {
		String query = String.format("SELECT balance FROM account WHERE acco_id = '%s'", id);
		Double balance = jdbcTemplate.queryForObject(query, Double.class);
		return new BalanceDTO(balance);
	}
	
}


class BalanceDTO {
	
	private Double balance;
	
	public BalanceDTO(Double balance) {
		super();
		this.balance = balance;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
