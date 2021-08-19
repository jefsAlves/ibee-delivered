package com.example.food.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItens {

	@JsonProperty("product")
	private Long productId;

	@JsonProperty("quantity")
	private Long quantity;

	@JsonProperty("observation")
	private String observation;
}
