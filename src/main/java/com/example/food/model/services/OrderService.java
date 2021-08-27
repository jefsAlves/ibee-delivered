package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrderInputDTO;
import com.example.food.model.dto.OrdersDTO;
import com.example.food.model.entities.Order;

public interface OrderService {

	OrderDTO searchOrder(Long orderId);

	Order searchByOrder(Long orderId);

	List<OrdersDTO> listOrders();

	OrderDTO createOrder(OrderInputDTO orderInputDTO);

}
