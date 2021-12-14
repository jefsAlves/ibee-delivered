package com.example.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.api.dto.PaymentDTO;
import com.example.food.model.services.PaymentService;

@RestController
@RequestMapping(value = "/payment-form")
public class PaymentsController {

	@Autowired
	private PaymentService paymentForm;

	@GetMapping
	public ResponseEntity<List<PaymentDTO>> searchFormPayments() {
		return new ResponseEntity<>(paymentForm.listPayments(), HttpStatus.OK);
	}

}
