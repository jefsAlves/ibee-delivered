package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderDTO {

	private String orderCode;
	private BigDecimal subTotal;
	private BigDecimal freigthRate;
	private BigDecimal totalValue;
	private String status;
	private LocalDateTime createDate;
	private LocalDateTime confirmationDate;
	private LocalDateTime cancelledDate;
	private LocalDateTime deliveredDate;
	private RestaurantsDTO restaurant;
	private UsersDTO users;
	private PaymentsDTO payments;
	private AddressDTO address;

	private List<OrderItemDTO> orderItem;

}
