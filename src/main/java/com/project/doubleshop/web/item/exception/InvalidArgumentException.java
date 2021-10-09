package com.project.doubleshop.web.item.exception;

public class InvalidItemArgumentException extends IllegalArgumentException {
	public InvalidItemArgumentException() {
		super();
	}

	public InvalidItemArgumentException(String s) {
		super(s);
	}

	public InvalidItemArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidItemArgumentException(Throwable cause) {
		super(cause);
	}
}
