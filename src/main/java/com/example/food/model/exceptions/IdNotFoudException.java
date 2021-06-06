package com.example.food.model.exceptions;

import java.io.Serializable;

public class IdNotFoudException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public IdNotFoudException() {
	}

	public IdNotFoudException(String msg) {
		super(msg);
	}

}
