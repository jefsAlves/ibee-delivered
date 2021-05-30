package com.example.food.model.dto;

import java.math.BigDecimal;

import com.example.food.model.entities.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RestaurantDTO {

	@EqualsAndHashCode.Include
	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "freigthRate")
	private BigDecimal freigthRate;

	@JsonProperty(value = "kitchen")
	private Kitchen kitchen;

}
