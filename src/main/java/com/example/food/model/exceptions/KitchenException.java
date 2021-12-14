package com.example.food.model.exceptions;

import java.io.Serializable;

public class KitchenException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public KitchenException() {
	}

	public KitchenException(String msg) {
		super(msg);
	}

}
