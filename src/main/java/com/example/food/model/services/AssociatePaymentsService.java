package com.example.food.model.services;

public interface AssociatePaymentsService {

	void associatePaymentToRestaraunt(Long restaurantId, Long paymentFormId);

	void desassociatePaymentToRestaurant(Long restaurantId, Long paymentFormId);
}
