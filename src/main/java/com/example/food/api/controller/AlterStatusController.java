package com.example.food.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.api.dto.OrderDTO;
import com.example.food.model.services.AlterStatusService;

@RestController
@RequestMapping("/order/{orderCode}")
public class AlterStatusController {

	@Autowired
	private AlterStatusService alterStatus;

	@PutMapping("/confirmation")
	public ResponseEntity<Void> alterStatusConfirmation(@PathVariable String orderCode) {
		alterStatus.alterStatusConfirmation(orderCode);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/delivered")
	public ResponseEntity<Void> alterStatusDelivered(@PathVariable String orderCode) {
		alterStatus.alterStatusDelivered(orderCode);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/cancelled")
	public ResponseEntity<OrderDTO> alterStatusCancelled(@PathVariable String orderCode) {
		alterStatus.alterStatusCancelled(orderCode);
		return ResponseEntity.noContent().build();
	}
}
