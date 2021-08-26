package com.example.food.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderInputDTO {

	@JsonProperty("restaurant")
	private RestaurantsDTO restaurant;

	@JsonProperty("payment")
	private PaymentsDTO payments;

	@JsonProperty("address")
	private AddressDTO address;
	
	@JsonProperty("user")
	private UsersDTO users;
	
	@JsonProperty("orderItem")
	private List<OrderItemInputDTO> orderItem;

}
