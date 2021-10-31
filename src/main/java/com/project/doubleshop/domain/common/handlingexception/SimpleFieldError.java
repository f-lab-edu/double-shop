package com.project.doubleshop.domain.common.handlingexception;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.project.doubleshop.domain.common.handlingexception.exception.ExplicitConstraintViolationException;

public class SimpleFieldError {

	private String field;

	private String code;

	private String message;

	private SimpleFieldError() {
	}

	public SimpleFieldError(String field, String code, String message) {
		this.field = field;
		this.code = code;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static Collection<SimpleFieldError> getErrors(ExplicitConstraintViolationException exception) {

		return exception.getConstraintViolations().stream()
			.map(constraintViolation ->
				new SimpleFieldError(
					constraintViolation.getPropertyPath().toString(),
					constraintViolation.getMessageTemplate(),
					constraintViolation.getMessage()))
			.collect(Collectors.toList());
	}

	public static List<SimpleFieldError> getErrors(Set<ConstraintViolation<?>> constraintViolations) {

		return constraintViolations.stream()
			.map(SimpleFieldError::of).collect(Collectors.toList());
	}

	private static SimpleFieldError of(ConstraintViolation<?> constraintViolation) {

		// propertyPath의 첫 부분을 제거함으로서 필드명을 얻는다.
		// (대부분의 경우, 서비스 메소드명이 propertyPath의 첫 부분이다.)
		String field = StringUtils.substringAfter(constraintViolation.getPropertyPath().toString(), ".");

		return new SimpleFieldError(field,
			constraintViolation.getMessageTemplate(),
			constraintViolation.getMessage());
	}

	public static List<SimpleFieldError> getErrors(WebExchangeBindException exception) {

		List<SimpleFieldError> errors = exception.getFieldErrors().stream()
			.map(SimpleFieldError::of).collect(Collectors.toList());

		errors.addAll(exception.getGlobalErrors().stream()
			.map(SimpleFieldError::of).collect(Collectors.toSet()));

		return errors;
	}

	private static SimpleFieldError of(FieldError fieldError) {

		return new SimpleFieldError(fieldError.getObjectName() + "." + fieldError.getField(),
			fieldError.getCode(), fieldError.getDefaultMessage());
	}

	public static SimpleFieldError of(ObjectError error) {

		return new SimpleFieldError(error.getObjectName(),
			error.getCode(), error.getDefaultMessage());
	}
}
