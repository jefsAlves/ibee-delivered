package com.example.food.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderItemInputDTO {

	@JsonProperty("productId")
	private Long productsId;
	
	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("observation")
	private String observation;

}
