package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.RestaurantDTO;
import com.example.food.model.entities.Restaurant;

public interface RestaurantService {

	Restaurant search(Long restaurantId);

	RestaurantDTO searchRestaurant(Long id);

	RestaurantDTO searchRestaurantByName(String name);

	List<Restaurant> listRestaurants(Restaurant restaurant);

	RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

	RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurant);

	void deleteRestaurant(Long id);

	void activeRestaurant(Long id);

	void desactiveRestaurant(Long id);

}
