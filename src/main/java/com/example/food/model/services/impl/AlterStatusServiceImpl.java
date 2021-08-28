package com.example.food.model.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.services.AlterStatusService;
import com.example.food.model.services.OrderService;

@Service
public class AlterStatusServiceImpl implements AlterStatusService {

	@Autowired
	private OrderService orderService;

	@Transactional
	@Override
	public void alterStatusConfirmation(String orderCode) {
		var order = orderService.searchByOrder(orderCode);
		order.confirmeted();
	}

	@Transactional
	@Override
	public void alterStatusDelivered(String orderCode) {
		var order = orderService.searchByOrder(orderCode);
		order.delivered();
	}

	@Transactional
	@Override
	public void alterStatusCancelled(String orderCode) {
		var order = orderService.searchByOrder(orderCode);
		order.cancelled();
	}

}
