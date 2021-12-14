package com.example.food.model.exceptions;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String msg) {
		super(msg);
	}

}
