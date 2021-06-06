package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.Restaurant;
import com.example.food.model.exceptions.RestaurantException;
import com.example.food.model.repository.RestaurantRepository;

@Component
public class ValidationRestaurant {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public boolean verifyRestaurantExist(String name) {
		Restaurant restaurantValid = restaurantRepository.findByName(name);
		if (restaurantValid != null) {
			throw new RestaurantException(MessageUtil.KITCHEN_ALREADY_EXIST);
		}

		return true;
	}

	public Restaurant verifyRestaurantExist(Long id) {
		Optional<Restaurant> restaurantValid = restaurantRepository.findById(id);
		restaurantValid.orElseThrow(() -> new RestaurantException(MessageUtil.ID_NOT_FOUND));

		return restaurantValid.get();
	}

}
