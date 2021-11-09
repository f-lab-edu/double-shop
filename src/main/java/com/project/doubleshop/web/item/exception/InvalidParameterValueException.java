package com.project.doubleshop.web.item.exception;

public class InvalidParameterValueException extends RuntimeException {
	public InvalidParameterValueException() {
		super();
	}

	public InvalidParameterValueException(String message) {
		super(message);
	}

	public InvalidParameterValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParameterValueException(Throwable cause) {
		super(cause);
	}

	protected InvalidParameterValueException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
