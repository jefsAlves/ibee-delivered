package com.example.food.model.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupsDTO {

	@JsonProperty("group")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("permission")
	private Set<PermissionsDTO> permissions;

}
