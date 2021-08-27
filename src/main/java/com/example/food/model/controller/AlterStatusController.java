package com.example.food.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.OrderDTO;
import com.example.food.model.services.AlterStatusService;

@RestController
@RequestMapping("/order/{orderId}")
public class AlterStatusController {

	@Autowired
	private AlterStatusService alterStatus;

	@PutMapping("/confirmation")
	public ResponseEntity<Void> alterStatusConfirmation(@PathVariable Long orderId) {
		alterStatus.alterStatusConfirmation(orderId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/delivered")
	public ResponseEntity<Void> alterStatusDelivered(@PathVariable Long orderId) {
		alterStatus.alterStatusDelivered(orderId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/cancelled")
	public ResponseEntity<OrderDTO> alterStatusCancelled(@PathVariable Long orderId) {
		alterStatus.alterStatusCancelled(orderId);
		return ResponseEntity.noContent().build();
	}
}