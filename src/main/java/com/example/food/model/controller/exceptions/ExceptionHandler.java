package com.example.food.model.controller.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.food.model.entities.enums.ProblemType;
import com.example.food.model.exceptions.CityException;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.exceptions.KitchenException;
import com.example.food.model.exceptions.RestaurantException;
import com.example.food.model.exceptions.StateException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoudException.class)
	public ResponseEntity<?> idNotFoundException(IdNotFoudException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.NOT_FOUND;
		ProblemType problem = ProblemType.ID_NOT_FOUND;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(KitchenException.class)
	public ResponseEntity<?> kitchenException(KitchenException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ProblemType problem = ProblemType.KITCHEN_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(RestaurantException.class)
	public ResponseEntity<?> restaurantException(RestaurantException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problem = ProblemType.KITCHEN_ALREADY_EXIST;
		String message = e.getMessage();
		ResponseBody body = createBuilderResponseBody(status, problem, message).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(CityException.class)
	public ResponseEntity<?> cityException(CityException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ProblemType problem = ProblemType.CITY_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(StateException.class)
	public ResponseEntity<?> stateException(StateException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ProblemType problem = ProblemType.STATE_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormartException((InvalidFormatException) rootCause, headers, status, request);
		}

		if (rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizesPropertyException((UnrecognizedPropertyException) rootCause, headers, status,
					request);
		}

		if (rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, status, headers, request);
		}

		ProblemType problem = ProblemType.ERROR_BODY;
		String detail = "Invalid body!";
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormartException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ProblemType problem = ProblemType.ERROR_BODY;
		String detail = String.format("Invalid value for the propertie %s", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();

		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizesPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ProblemType problem = ProblemType.ERROR_BODY;
		String detail = String.format("The propertie %s not exist", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex, HttpStatus status, HttpHeaders headers, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ProblemType problem = ProblemType.ERROR_BODY;
		String detail = String.format("The propertie %s is not mapped", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private String concatProperties(List<Reference> reference) {
		return reference.stream()
				.map(e -> e.getFieldName())
				.collect(Collectors.joining("."));
	}

	private ResponseBody.ResponseBodyBuilder createBuilderResponseBody(HttpStatus statusCode, ProblemType problemType, String detail) {
		return ResponseBody.builder().status(statusCode.value()).type(problemType.getUri())
				.title(problemType.getTitle()).detail(detail).timestamp(LocalDateTime.now());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
