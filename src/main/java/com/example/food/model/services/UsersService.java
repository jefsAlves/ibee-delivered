package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.UserDTO;
import com.example.food.model.dto.UserPasswordDTO;

public interface UsersService {

	List<UserDTO> listsUsers();

	UserDTO searchUser(Long userId);

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long user, UserDTO userDTO);

	void updatePassword(Long userId, UserPasswordDTO userPasswordDTO);

	void deleteUser(Long user);

}
