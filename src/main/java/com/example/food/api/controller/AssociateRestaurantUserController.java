package com.example.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.api.dto.UserDTO;
import com.example.food.model.services.AssociateRestaurantUserService;

@RestController
@RequestMapping(value = "/restaurant/{restaurantId}")
public class AssociateRestaurantUserController {

	@Autowired
	private AssociateRestaurantUserService associateRestaurantUserService;

	@GetMapping(value = "/user}")
	public ResponseEntity<List<UserDTO>> listUsers(@PathVariable Long restaurantId) {
		return new ResponseEntity<>(associateRestaurantUserService.listUsers(restaurantId), HttpStatus.OK);
	}

	@PutMapping(value = "/user/{userId}")
	public ResponseEntity<Void> associateRestaurantToUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
		associateRestaurantUserService.associateRestaurantToUser(restaurantId, userId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<Void> desassociateRestaurantUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
		associateRestaurantUserService.desassociateRestaurantToUser(restaurantId, userId);
	 	return ResponseEntity.noContent().build();
	}

}
