package com.example.food.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {

	private Long id;

	private BigDecimal value;

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
