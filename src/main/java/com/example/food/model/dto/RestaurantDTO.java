package com.example.food.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("freigthRate")
	private BigDecimal freigthRate;

	@JsonProperty("kitchen")
	private KitchenDTO kitchenDTO;

	@JsonProperty("statusActive")
	private Boolean statusCode;

	@JsonProperty("address")
	private AddressDTO addressDTO;

}
