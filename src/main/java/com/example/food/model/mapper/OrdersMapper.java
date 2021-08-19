package com.example.food.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.OrdersDTO;
import com.example.food.model.entities.Order;

@Component
public class OrdersMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrdersDTO toDTO(Order order) {
		return modelMapper.map(order, OrdersDTO.class);
	}

	public List<OrdersDTO> toDTOList(List<Order> order) {
		return order.stream().map(src -> toDTO(src)).collect(Collectors.toList());
	}
	
}
