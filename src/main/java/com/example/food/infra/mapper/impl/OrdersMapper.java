package com.example.food.infra.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.OrdersDTO;
import com.example.food.model.entities.Order;

@Component
public class OrdersMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrdersDTO toDTO(Order order) {
		return modelMapper.map(order, OrdersDTO.class);
	}

	public List<OrdersDTO> toDTOList(List<Order> orders) {
		return orders.stream().map(order -> toDTO(order)).collect(Collectors.toList());
	}
}
