package com.example.food.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("user")
	private String user;

}
