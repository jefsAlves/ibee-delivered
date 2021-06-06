package com.example.food.model.controller.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {

	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
