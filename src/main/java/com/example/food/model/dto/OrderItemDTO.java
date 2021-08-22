package com.example.food.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderItemDTO {

	@JsonProperty("productId")
	private Long productsId;

	@JsonProperty("description")
	private String productsDescription;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("unitaryPrice")
	private BigDecimal unitaryPrice;

	@JsonProperty("totalPrice")
	private BigDecimal totalPrice;

	@JsonProperty("observation")
	private String observation;

}
