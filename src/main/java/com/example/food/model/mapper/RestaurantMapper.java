package com.example.food.model.mapper;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

	public Restaurant toEntity(RestaurantDTO restaurantDTO) {
		return modelMapper.map(restaurantDTO, Restaurant.class);
	}

	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants) {
		return modelMapper.map(restaurants, new TypeToken<List<Restaurant>>() {}.getType());
	}

	public List<Restaurant> toEntityList(List<RestaurantDTO> restaurantDTO) {
		return modelMapper.map(restaurantDTO, new TypeToken<List<RestaurantDTO>>() {}.getType());
	}

	public RestaurantDTO toDTO(Optional<Restaurant> restaurant) {
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		restaurantDTO.setId(restaurant.get().getId());
		restaurantDTO.setName(restaurant.get().getName());
		restaurantDTO.setFreigthRate(restaurant.get().getFreigthRate());
		restaurantDTO.setStatus(restaurant.get().getStatus());
		restaurantDTO.setOpen(restaurant.get().getOpen());
		return restaurantDTO;
	}

	public void copyProperties(RestaurantDTO restaurantDTO, Restaurant restaurant) {
		restaurant.setKitchen(new Kitchen());
		modelMapper.map(restaurantDTO, restaurant);
	}
}
