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

	@JsonProperty("status")
	private Boolean status;

	@JsonProperty("open")
	private Boolean open;
	
	@JsonProperty("kitchen")
	private KitchenDTO kitchenDTO;

	@JsonProperty("address")
	private AddressDTO addressDTO;

}
