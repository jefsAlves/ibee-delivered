package com.example.food.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.dto.OrderNewDTO;
import com.example.food.model.dto.OrdersDTO;
import com.example.food.model.services.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderNewDTO order) {
		return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{orderId}")
	public ResponseEntity<OrderDTO> searchOrder(@PathVariable Long orderId) {
		return new ResponseEntity<>(orderService.searchOrder(orderId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<OrdersDTO>> listOrder() {
		return new ResponseEntity<>(orderService.listOrders(), HttpStatus.OK);
	}

}
