package com.example.food.model.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

	CREATE(0L),
	DELIVERED(1L),
	CANCELLED(2L),
	CONFIRMED(3L);
	
	private Long numberOrder;

}
