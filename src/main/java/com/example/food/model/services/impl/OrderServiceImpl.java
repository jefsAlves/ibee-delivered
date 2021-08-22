package com.example.food.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrdersDTO;
import com.example.food.model.entities.Order;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.OrderMapper;
import com.example.food.model.mapper.OrdersMapper;
import com.example.food.model.repository.OrderRepository;
import com.example.food.model.services.OrderService;
import com.example.food.model.util.MessageUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper mapper;

	@Autowired
	private OrdersMapper mappers;

	@Override
	public OrderDTO searchOrder(Long orderId) {
		var order = orderRepository.search(orderId);
		if (order == null) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
		return mapper.toDTO(order);
	}

	@Override
	public List<OrdersDTO> listOrders() {
		List<Order> order = orderRepository.findAll();
		return mappers.toDTOList(order);
	}
}
