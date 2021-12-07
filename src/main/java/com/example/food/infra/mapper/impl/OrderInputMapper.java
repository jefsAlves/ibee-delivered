package com.example.food.infra.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.OrderInputDTO;
import com.example.food.model.entities.Order;

@Component
public class OrderInputMapper {

	@Autowired
	private ModelMapper	modelMapper;
	
	public Order toEntity(OrderInputDTO orderInputDTO) {
		return modelMapper.map(orderInputDTO, Order.class);
	}
	
}
