package com.example.food.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.UserDTO;
import com.example.food.model.dto.UserPasswordDTO;
import com.example.food.model.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

	@Autowired
	private UserService usersService;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> searchUser(@PathVariable Long userId) {
		return new ResponseEntity<>(usersService.searchUser(userId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> listsUsers() {
		return new ResponseEntity<>(usersService.listsUsers(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO usersDTO) {
		return new ResponseEntity<>(usersService.createUser(usersDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO usersDTO) {
		return new ResponseEntity<>(usersService.updateUser(userId, usersDTO), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/{userId}/password")
	public ResponseEntity<Void> updateUserPassword(@PathVariable Long userId, @RequestBody UserPasswordDTO userPasswordDTO) {
		usersService.updatePassword(userId, userPasswordDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		usersService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

}
