package com.example.food.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.services.AssociatePaymentsService;

@RestController
@RequestMapping(value = "/restaurant/{restaurantId}/paymentform")
public class PaymentsAssociateController {

	@Autowired
	private AssociatePaymentsService paymentsAssociateService;

	@PutMapping(value = "/{paymentFormId}")
	public ResponseEntity<Void> associtePayment(@PathVariable Long restaurantId, @PathVariable Long paymentFormId) {
		paymentsAssociateService.associatePaymentToRestaraunt(restaurantId, paymentFormId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{paymentFormId}")
	public ResponseEntity<Void> desassociatePayment(@PathVariable Long restaurantId, @PathVariable Long paymentFormId) {
		paymentsAssociateService.desassociatePaymentToRestaurant(restaurantId, paymentFormId);
		return ResponseEntity.noContent().build();
	}

}
