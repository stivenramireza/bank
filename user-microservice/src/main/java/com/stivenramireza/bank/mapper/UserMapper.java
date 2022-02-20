package com.stivenramireza.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.stivenramireza.bank.domain.User;
import com.stivenramireza.bank.dto.UserDTO;

@Mapper
public interface UserMapper {

	@Mapping(source = "userType.ustyId", target = "ustyId")
	public UserDTO userToUserDTO(User user);
	
	@Mapping(source = "ustyId", target = "userType.ustyId")
	public User userDTOToUser(UserDTO userDTO);
	
	public List<UserDTO> userListToUserDTOList(List<User> users);
	
	public List<User> userDTOListToUserList(List<UserDTO> userDTOs);
	
}
