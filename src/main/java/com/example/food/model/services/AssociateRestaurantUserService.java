package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.UserDTO;

public interface AssociateRestaurantUserService {

	List<UserDTO> listUsers(Long restaurantId);

	void associateRestaurantToUser(Long restaurandId, Long userId);

	void desassociateRestaurantToUser(Long restaurantId, Long userId);

}
