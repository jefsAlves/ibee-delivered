package com.example.food.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KitchenDTO {

	private Long id;
	private String name;
	private List<RestaurantDTO> restaurantDTO;

}
