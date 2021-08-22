package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.food.model.entities.Address;
import com.example.food.model.entities.OrderItem;
import com.example.food.model.entities.Payment;
import com.example.food.model.entities.Restaurant;
import com.example.food.model.entities.User;
import com.example.food.model.entities.enums.OrderStatus;

import lombok.Data;

@Data
public class OrdersDTO {

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal freigthRate;
	private BigDecimal totalValue;
	private LocalDateTime createDate;
	private LocalDateTime confirmationDate;
	private LocalDateTime cancelledDate;
	private LocalDateTime deliveredDate;
	private Address address;
	private OrderStatus status;
	private Payment payments;
	private Restaurant restaurant;
	private User users;

	private List<OrderItem> orderItem;
}
