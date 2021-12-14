package com.example.food.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonIgnore
	private List<RestaurantDTO> restaurantDTO;

}
