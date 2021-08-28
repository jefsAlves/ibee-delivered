package com.example.food.model.entities.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum OrderStatus {

	CREATE(),
	CONFIRMED(CREATE),
	DELIVERED(CONFIRMED),
	CANCELLED(CREATE);

	private List<OrderStatus> previousStatus;
	
	private OrderStatus(OrderStatus... previousStatus) {
		this.previousStatus = Arrays.asList(previousStatus);
	}
	
	public boolean cannotBeAlterStatus(OrderStatus newStatus) {
		return !newStatus.previousStatus.contains(this);
	}
}
