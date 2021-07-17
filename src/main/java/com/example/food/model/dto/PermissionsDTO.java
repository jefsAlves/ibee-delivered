package com.example.food.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionsDTO {

	@JsonProperty("permission")
	private Long permissionId;

	@JsonProperty("description")
	private String description;

}
