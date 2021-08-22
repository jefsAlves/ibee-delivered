package com.example.food.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.entities.Order;
import com.example.food.model.mapper.OrderMapper;
import com.example.food.model.repository.OrderRepository;
import com.example.food.model.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper mapper;

	@Override
	public List<OrderDTO> listOrders() {
		List<Order> order = orderRepository.findAll();
		return mapper.toDTOList(order);
	}
}
