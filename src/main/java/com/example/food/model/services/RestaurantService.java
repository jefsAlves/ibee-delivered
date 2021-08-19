package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.RestaurantDTO;
import com.example.food.model.entities.Restaurant;

public interface RestaurantService {

	Restaurant search(Long restaurantId);

	RestaurantDTO searchRestaurant(Long restaurantId);

	RestaurantDTO searchRestaurantByName(String name);

	List<Restaurant> listRestaurants(Restaurant restaurant);

	RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

	RestaurantDTO updateRestaurant(Long restaurantId, RestaurantDTO restaurant);

	void deleteRestaurant(Long restaurantId);

	void activeRestaurant(Long restaurantId);

	void desactiveRestaurant(Long restaurantId);

	void activeAll(List<Long> restaurantIds);

	void desactiveRestaurantAll(List<Long> restaurantIds);

	void openRestaurant(Long restaurantId);

	void closeRestaurant(Long restaurantId);

}
