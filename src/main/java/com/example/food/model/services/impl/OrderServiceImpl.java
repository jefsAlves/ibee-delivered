package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrderNewDTO;
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
		Optional<Order> order = orderRepository.findById(orderId);
		order.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(order);
	}

	@Override
	public List<OrdersDTO> listOrders() {
		var orders = orderRepository.findAll();
		return mappers.toDTOList(orders);
	}

	@Transactional
	@Override
	public OrderDTO createOrder(OrderNewDTO orderDTO) {
		var order = mapper.toEntity(orderDTO);
//		order.setFreigthRate(order.getRestaurant().getFreigthRate());
		order.calculateTotal();
		orderRepository.save(order);
		return mapper.toDTO(order);
	}

}
