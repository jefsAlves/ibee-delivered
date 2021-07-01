package com.example.food.model.dto;

import java.math.BigDecimal;

import com.example.food.model.entities.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {

	private Long id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "freigthRate")
	private BigDecimal freigthRate;

	@JsonProperty(value = "kitchen")
	private Kitchen kitchen;

}
