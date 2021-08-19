package com.example.food.model.dto;

import java.math.BigDecimal;

import com.example.food.model.entities.Order;
import com.example.food.model.entities.Products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

	private Long id;

	private Integer quantity;

	private BigDecimal totalPrice;

	private String observation;

	private Order order;

	private Products products;
}
