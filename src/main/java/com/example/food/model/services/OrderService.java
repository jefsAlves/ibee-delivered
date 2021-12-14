package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.OrderDTO;
import com.example.food.api.dto.OrderInputDTO;
import com.example.food.api.dto.OrdersDTO;
import com.example.food.model.entities.Order;

public interface OrderService {

	OrderDTO searchOrder(String orderCode);

	Order searchByOrder(String orderCode);

	List<OrdersDTO> listOrders();

	OrderDTO createOrder(OrderInputDTO orderInputDTO);

}
