package com.example.food.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StateDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;
}
