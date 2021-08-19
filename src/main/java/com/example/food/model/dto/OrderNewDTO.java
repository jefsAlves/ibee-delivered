package com.example.food.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNewDTO {

	@JsonProperty("restaurant")
	private RestaurantsDTO restaurant;

	@JsonProperty("payment")
	private PaymentsDTO payments;

	@JsonProperty("address")
	private AddressDTO address;

	@JsonProperty("itens")
	private List<OrderItens> orderItem;
	
}
