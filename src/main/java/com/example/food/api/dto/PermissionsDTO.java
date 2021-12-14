package com.example.food.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionsDTO {

	@JsonProperty("permission")
	private Long id;

	@JsonProperty("description")
	private String description;

}
