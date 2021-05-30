package com.example.food.model.services;

import java.util.List;

import com.example.food.model.entities.Restaurant;

public interface RestaurantService {

	Restaurant searchRestaurant(Long id);

	Restaurant searchRestaurantByName(String name);

	List<Restaurant> listsRestaurants(Restaurant restaurant);

	Restaurant createRestaurant(Restaurant restaurant);

	Restaurant updateRestaurant(Long id, Restaurant restaurant);

	void deleteRestaurant(Long id);

}
