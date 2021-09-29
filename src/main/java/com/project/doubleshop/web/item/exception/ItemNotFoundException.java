package com.project.doubleshop.web.item.exception;

public class ItemNotFoundException extends RuntimeException {
	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}

	protected ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
