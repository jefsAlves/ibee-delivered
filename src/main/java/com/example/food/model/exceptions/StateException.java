package com.example.food.model.exceptions;

import java.io.Serializable;

public class StateException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public StateException() {
	}

	public StateException(String msg) {
		super(msg);
	}

}
