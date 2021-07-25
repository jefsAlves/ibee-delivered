package com.example.food.model.entities.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

	ID_NOT_FOUND("not found", "not-found"),
	RESTAURANT_ALREADY_EXIST("/restaurant", "resource already exist"),
	KITCHEN_ALREADY_EXIST("kitchen", "resource already exist"),
	CITY_ALREADY_EXIST("city", "resource already exist"),
	STATE_ALREADY_EXIST("state", "resource already exist"),
	EMAIL_ALREADY_EXIST("email", "resource already exist"),
	INVALID_BODY("", "error body"),
	INVALID_PARAM("", "invalid param in url"),
	INVALID_RESOURCE("", "resource not found"),
	UNEXPECTED_ERROR("", "unexpected error ocurred");

	private String uri;
	private String title;

	private ErrorType(String uri, String path) {
		this.uri = "http://localhost:8080/" + uri;
		title = path;
	}

}
