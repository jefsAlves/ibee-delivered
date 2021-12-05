package com.example.food.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.example.food.model.entities.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	@JsonProperty("id")
	private Long id;

	@NotBlank
	@JsonProperty("description")
	private String description;

	@NotBlank
	@JsonProperty("price")
	private BigDecimal price;

	@NotBlank
	@JsonProperty("active")
	private Boolean active;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Restaurant restaurant;

}
