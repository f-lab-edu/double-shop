package com.project.doubleshop.domain.common.handlingexception;

import java.util.Collection;

public class ErrorResponse {

	private String exception;

	private Integer status;

	private String error;

	private String message;

	private Collection<SimpleFieldError> fieldErrors;

	public boolean incomplete() {
		return message == null || status == null;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Collection<SimpleFieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Collection<SimpleFieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
