package com.example.food.model.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("user")
	private String user;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonIgnore
	private Set<GroupDTO> groups;

}
