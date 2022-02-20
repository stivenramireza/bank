package com.stivenramireza.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stivenramireza.bank.domain.User;
import com.stivenramireza.bank.dto.UserDTO;
import com.stivenramireza.bank.mapper.UserMapper;
import com.stivenramireza.bank.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable("id") String id) throws Exception {
		Boolean existsUser = userService.findById(id).isPresent();
		User user = existsUser ? userService.findById(id).get() : null;
		UserDTO userDTO = userMapper.userToUserDTO(user);
		return userDTO;
	}
	
	@GetMapping
	public List<UserDTO> findAll() throws Exception {
		List<User> users = userService.findAll();
		List<UserDTO> userDTOs = userMapper.userListToUserDTOList(users);
		return userDTOs;
	}
	
	@PostMapping
	public UserDTO save(@Valid @RequestBody UserDTO userDTO) throws Exception {
		User user = userMapper.userDTOToUser(userDTO);
		user = userService.save(user);
		userDTO = userMapper.userToUserDTO(user);
		return userDTO;
	}
	
	@PutMapping
	public UserDTO update(@Valid @RequestBody UserDTO userDTO) throws Exception {
		User user = userMapper.userDTOToUser(userDTO);
		user = userService.update(user);
		userDTO = userMapper.userToUserDTO(user);
		return userDTO;
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) throws Exception {
		userService.deleteById(id);
	}
	
	@GetMapping("/count")
	public Long count() {
		Long counter = userService.count();
		return counter;
	}

}
