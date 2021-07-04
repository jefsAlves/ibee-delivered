package com.example.food.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {

	private Long id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "freigthRate")
	private BigDecimal freigthRate;

	@JsonProperty(value = "kitchen")
	private KitchenDTO kitchenDTO;

}
