package com.example.food.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantsDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

}
