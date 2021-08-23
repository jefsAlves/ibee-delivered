package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.food.model.entities.enums.OrderStatus;

import lombok.Data;

@Data
public class OrdersDTO {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal freigthRate;
	private BigDecimal totalValue;
	private LocalDateTime createDate;
	private OrderStatus status;
	private RestaurantsDTO restaurant;
	private UsersDTO users;

}
