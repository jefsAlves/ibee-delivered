package com.example.food.model.entities.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

	ID_NOT_FOUND("not found", "not-found"),
	RESTAURANT_ALREADY_EXIST("/restaurant", "resource already exist"),
	KITCHEN_ALREADY_EXIST("kitchen", "resource already exist"),
	CITY_ALREADY_EXIST("city", "resource already exist"),
	STATE_ALREADY_EXIST("state", "resource already exist"),
	ERROR_BODY("", "error body");

	private String uri;
	private String title;

	private ProblemType(String uri, String path) {
		this.uri = "http://localhost:8080/" + uri;
		title = path;
	}

}
