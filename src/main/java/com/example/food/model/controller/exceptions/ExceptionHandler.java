package com.example.food.model.controller.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.food.model.entities.ResponseBodyFields;
import com.example.food.model.entities.enums.ErrorType;
import com.example.food.model.exceptions.BusinessException;
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

	@Autowired
	private MessageSource messageSource;

	@org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoudException.class)
	public ResponseEntity<?> idNotFoundException(IdNotFoudException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.NOT_FOUND;
		ErrorType problem = ErrorType.ID_NOT_FOUND;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(KitchenException.class)
	public ResponseEntity<?> kitchenException(KitchenException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ErrorType problem = ErrorType.KITCHEN_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(RestaurantException.class)
	public ResponseEntity<?> restaurantException(RestaurantException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorType problem = ErrorType.KITCHEN_ALREADY_EXIST;
		String message = e.getMessage();
		ResponseBody body = createBuilderResponseBody(status, problem, message).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(CityException.class)
	public ResponseEntity<?> cityException(CityException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ErrorType problem = ErrorType.CITY_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(StateException.class)
	public ResponseEntity<?> stateException(StateException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ErrorType problem = ErrorType.STATE_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> businessException(BusinessException e, WebRequest request) {
		HttpStatus statusCode = HttpStatus.BAD_REQUEST;
		ErrorType problem = ErrorType.EMAIL_ALREADY_EXIST;
		String detail = e.getMessage();
		ResponseBody body = createBuilderResponseBody(statusCode, problem, detail).build();
		return handleExceptionInternal(e, body, new HttpHeaders(), statusCode, request);
	}

	public ResponseEntity<Object> unexpectedError(Exception ex, HttpStatus status, HttpHeaders headers,
			WebRequest request) {
		ErrorType error = ErrorType.UNEXPECTED_ERROR;
		String detail = "An unexpected error occurred, if the error persists, contact your system administrator";
		ResponseBody body = createBuilderResponseBody(status, error, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();

		List<ResponseBodyFields> responseBody = bindingResult.getFieldErrors().stream().map(fields -> {
			String message = messageSource.getMessage(fields, LocaleContextHolder.getLocale());

			return ResponseBodyFields.builder().name(fields.getField()).userMessage(message).build();
		}).collect(Collectors.toList());

		ErrorType error = ErrorType.INVALID_BODY;
		String detail = "Invalid body, please check the payload and enter the correct field values!";
		ResponseBody body = createBuilderResponseBody(status, error, detail).responseBodyFields(responseBody).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorType error = ErrorType.INVALID_RESOURCE;
		String detail = String.format("The resource %s", ex.getRequestURL());
		ResponseBody body = createBuilderResponseBody(status, error, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, status, headers, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormart((InvalidFormatException) rootCause, headers, status, request);
		}

		if (rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedProperty((UnrecognizedPropertyException) rootCause, headers, status, request);
		}

		if (rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredProperty((IgnoredPropertyException) rootCause, status, headers, request);
		}

		ErrorType problem = ErrorType.INVALID_BODY;
		String detail = "Invalid body!";
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormart(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ErrorType problem = ErrorType.INVALID_BODY;
		String detail = String.format("Invalid value for the propertie %s", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();

		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ErrorType problem = ErrorType.INVALID_BODY;
		String detail = String.format("The propertie %s not exist", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleIgnoredProperty(IgnoredPropertyException ex, HttpStatus status,
			HttpHeaders headers, WebRequest request) {
		String path = concatProperties(ex.getPath());
		ErrorType problem = ErrorType.INVALID_BODY;
		String detail = String.format("The propertie %s is not mapped", path);
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpStatus status, HttpHeaders headers, WebRequest request) {
		ErrorType problem = ErrorType.INVALID_PARAM;
		String detail = String.format("Invalid param '%s'", ex.getValue());
		ResponseBody body = createBuilderResponseBody(status, problem, detail).build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	private String concatProperties(List<Reference> reference) {
		return reference.stream().map(e -> e.getFieldName()).collect(Collectors.joining("."));
	}

	private ResponseBody.ResponseBodyBuilder createBuilderResponseBody(HttpStatus statusCode, ErrorType problemType, String detail) {
		return ResponseBody.builder()
		.status(statusCode.value())
		.type(problemType.getUri())
		.title(problemType.getTitle())
		.detail(detail)
		.timestamp(LocalDateTime.now());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
