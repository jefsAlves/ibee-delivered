package com.example.food.infra.mapper.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.UserDTO;
import com.example.food.model.entities.User;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO toDTO(User users) {
		return modelMapper.map(users, UserDTO.class);
	}

	public User toEntity(UserDTO usersDTO) {
		return modelMapper.map(usersDTO, User.class);
	}

	public List<UserDTO> toDTOList(List<User> users) {
		return modelMapper.map(users, new TypeToken<List<User>>() {}.getType());
	}

	public List<User> toEntityList(List<UserDTO> usersDTO) {
		return modelMapper.map(usersDTO, new TypeToken<List<UserDTO>>() {}.getType());
	}

	public Set<UserDTO> toDTOSet(Set<UserDTO> user) {
		return modelMapper.map(user, new TypeToken<Set<UserDTO>>() {}.getType());
	}

	public Set<User> toEntitySet(Set<UserDTO> user) {
		return modelMapper.map(user, new TypeToken<Set<UserDTO>>() {}.getType());
	}

	public UserDTO toDTO(Optional<User> user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.get().getId());
		userDTO.setUser(user.get().getUser());
		userDTO.setPassword(user.get().getPassword());
		return userDTO;
	}
	
	public static UserDTO toDTOOptional(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUser(user.getUser());
		return userDTO;
	}

	public void copyProperties(Optional<User> user, UserDTO userDTO) {
		userDTO.setId(user.get().getId());
		userDTO.setUser(user.get().getUser());
	}

	public void copyProperties(UserDTO usersDTO, User users) {
		BeanUtils.copyProperties(usersDTO, users, "id");
	}

}
