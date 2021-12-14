package com.example.food.model.exceptions;

import java.io.Serializable;

public class CannotDeleteException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public CannotDeleteException() {
	}

	public CannotDeleteException(String msg) {
		super(msg);
	}

}
