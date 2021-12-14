package com.example.food.model.services.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.services.AssociatePaymentsService;
import com.example.food.model.services.PaymentService;
import com.example.food.model.services.RestaurantService;

@Service
public class AssociatePaymentServiceImpl implements AssociatePaymentsService {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private PaymentService paymentService;

	@Transactional
	@Override
	public void associatePaymentToRestaraunt(Long restaurantId, Long paymentId) {
		var restaurant = restaurantService.search(restaurantId);
		var payment = paymentService.search(paymentId);
		restaurant.addPayment(payment);
	}

	@Transactional
	@Override
	public void desassociatePaymentToRestaurant(Long restaurantId, Long paymentId) {
		var restaurant = restaurantService.search(restaurantId);
		var payment = paymentService.search(paymentId);
		restaurant.removePayment(payment);
	}

}
