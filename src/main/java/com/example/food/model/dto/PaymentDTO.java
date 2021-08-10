package com.example.food.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;
}
