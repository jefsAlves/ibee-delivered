package com.example.food.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.entities.Order;

@Component
public class OrderMapper {

	@Autowired
	private ModelMapper modelMapper;

	public OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	public Order toEntity(OrderDTO orderDTO) {
		return modelMapper.map(orderDTO, Order.class);
	}

	public List<OrderDTO> toDTOList(List<Order> orders) {
		return orders.stream().map(order -> toDTO(order)).collect(Collectors.toList());
	}

}
