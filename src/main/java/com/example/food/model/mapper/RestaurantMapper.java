package com.example.food.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.RestaurantDTO;
import com.example.food.model.entities.Kitchen;
import com.example.food.model.entities.Restaurant;

@Component
public class RestaurantMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RestaurantDTO toDTO(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}

	public void copyProperties(RestaurantDTO restaurantDTO, Restaurant restaurant) {
		restaurant.setKitchen(new Kitchen());
		modelMapper.map(restaurantDTO, restaurant);
	}

	public Restaurant toEntity(RestaurantDTO restaurantDTO) {
		return modelMapper.map(restaurantDTO, Restaurant.class);
	}
}
