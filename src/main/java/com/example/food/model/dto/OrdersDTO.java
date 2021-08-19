package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("subTotal")
	private BigDecimal subtotal;

	@JsonProperty("freigthRate")
	private BigDecimal freigthRate;

	@JsonProperty("totalValue")
	private BigDecimal totalValue;

	@JsonProperty("status")
	private String status;

	@JsonProperty("createDate")
	private OffsetDateTime createDate;

	@JsonProperty("confirmationDate")
	private OffsetDateTime confirmationDate;

	@JsonProperty("delivetedDate")
	private OffsetDateTime develiredDate;

	@JsonProperty("canceledDate")
	private OffsetDateTime canceledDate;

	@JsonProperty("restaurant")
	private RestaurantDTO restaurantDTO;

	@JsonProperty("user")
	private UserDTO userDTO;

	@JsonProperty("payment")
	private PaymentDTO paymentDTO;

	@JsonProperty("address")
	private AddressDTO addressDTO;

}
