package com.example.food.model.services;

public interface AlterStatusService {

	void alterStatusConfirmation(String orderId);

	void alterStatusDelivered(String orderId);

	void alterStatusCancelled(String orderId);
}
