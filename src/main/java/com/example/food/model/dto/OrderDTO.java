package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class OrderDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("subTotal")
	private BigDecimal subTotal;

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
	private RestaurantDTO restaurant;

	@JsonProperty("user")
	private UserDTO users;

	@JsonProperty("payment")
	private PaymentDTO payments;

	@JsonProperty("address")
	private AddressDTO address;

	@JsonProperty("orderItem")
	private List<OrderItemDTO> orderItem;

}
