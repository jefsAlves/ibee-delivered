package com.example.food.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.dto.OrderNewDTO;
import com.example.food.model.repository.OrderRepository;

@Component
public class OrderUtil {

	@Autowired
	private OrderRepository orderRepository;

	public void validationOrder(OrderNewDTO order) {
	}

	public boolean verifyRestaurantExistToOrder(OrderNewDTO order) {
		return true;
	}

}
