package com.example.food.api.controller;

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

import com.example.food.api.dto.OrderDTO;
import com.example.food.api.dto.OrderInputDTO;
import com.example.food.api.dto.OrdersDTO;
import com.example.food.model.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{orderCode}")
	public ResponseEntity<OrderDTO> searchOrder(@PathVariable String orderCode) {
		return new ResponseEntity<>(orderService.searchOrder(orderCode), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<OrdersDTO>> listOrders() {
		return new ResponseEntity<>(orderService.listOrders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderInputDTO order) {
		return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
	}

}
