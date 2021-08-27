package com.example.food.model.services;

public interface AlterStatusService {

	void alterStatusConfirmation(Long orderId);

	void alterStatusDelivered(Long orderId);

	void alterStatusCancelled(Long orderId);
}
