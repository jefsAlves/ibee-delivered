package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrdersDTO;

public interface OrderService {

	OrderDTO searchOrder(Long orderId);

	List<OrdersDTO> listOrders();
}
