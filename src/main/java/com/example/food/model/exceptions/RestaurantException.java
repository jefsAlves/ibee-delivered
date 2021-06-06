package com.example.food.model.exceptions;

import java.io.Serializable;

public class RestaurantException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public RestaurantException() {
	}

	public RestaurantException(String msg) {
		super(msg);
	}
}
