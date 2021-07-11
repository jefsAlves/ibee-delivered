package com.example.food.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.PaymentsFormDTO;
import com.example.food.model.services.PaymentForm;

@RestController
@RequestMapping(value = "/payment-form")
public class PaymentFormController {

	@Autowired
	private PaymentForm paymentForm;

	@GetMapping
	public ResponseEntity<List<PaymentsFormDTO>> searchFormPayments() {
		return new ResponseEntity<>(paymentForm.searchPayments(), HttpStatus.OK);
	}

}
