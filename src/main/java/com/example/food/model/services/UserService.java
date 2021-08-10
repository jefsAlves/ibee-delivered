package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.UserDTO;
import com.example.food.model.dto.UserPasswordDTO;
import com.example.food.model.entities.User;

public interface UserService {

	User search(Long userId);
	
	UserDTO searchUser(Long userId);

	List<UserDTO> listsUsers();

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long user, UserDTO userDTO);

	void updatePassword(Long userId, UserPasswordDTO userPasswordDTO);

	void deleteUser(Long user);

}
