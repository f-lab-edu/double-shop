package com.project.doubleshop.web.item.exception;

public class InvalidArgumentException extends IllegalArgumentException {
	public InvalidArgumentException() {
		super();
	}

	public InvalidArgumentException(String s) {
		super(s);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}
}
