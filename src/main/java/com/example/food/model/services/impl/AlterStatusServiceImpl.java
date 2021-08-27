package com.example.food.model.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.enums.OrderStatus;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.services.AlterStatusService;
import com.example.food.model.services.OrderService;
import com.example.food.model.util.MessageUtil;

@Service
public class AlterStatusServiceImpl implements AlterStatusService {

	@Autowired
	private OrderService orderService;

	@Transactional
	@Override
	public void alterStatusConfirmation(Long orderId) {
		var order = orderService.searchByOrder(orderId);
		if (!order.getOrderStatus().equals(OrderStatus.CREATE)) {
			throw new BusinessException(MessageUtil.CANNOT_BE_ALTER_STATUS);
		}

		order.setOrderStatus(OrderStatus.CONFIRMED);
		order.setConfirmationDate(LocalDateTime.now());
	}

	@Transactional
	@Override
	public void alterStatusDelivered(Long orderId) {
		var order = orderService.searchByOrder(orderId);
		if (!order.getOrderStatus().equals(OrderStatus.CONFIRMED)) {
			throw new BusinessException(MessageUtil.CANNOT_BE_ALTER_STATUS);
		}

		order.setOrderStatus(OrderStatus.DELIVERED);
		order.setDeliveredDate(LocalDateTime.now());
	}

	@Transactional
	@Override
	public void alterStatusCancelled(Long orderId) {
		var order = orderService.searchByOrder(orderId);
		if (!order.getOrderStatus().equals(OrderStatus.CREATE)) {
			throw new BusinessException(MessageUtil.CANNOT_BE_ALTER_STATUS);
		}

		order.setOrderStatus(OrderStatus.CANCELLED);
		order.setCancelledDate(LocalDateTime.now());

	}
}
