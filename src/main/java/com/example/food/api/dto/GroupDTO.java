package com.example.food.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {

	@JsonProperty("group_id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("permission")
	private Set<PermissionsDTO> permissions;

}
