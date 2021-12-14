package com.example.food.model.exceptions;

import java.io.Serializable;

public class CityException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public CityException() {
	}

	public CityException(String msg) {
		super(msg);
	}

}
