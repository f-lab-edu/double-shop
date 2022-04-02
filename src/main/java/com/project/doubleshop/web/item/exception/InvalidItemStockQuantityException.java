package com.project.doubleshop.web.item.exception;

public class InvalidItemStockQuantityException extends RuntimeException {
	public InvalidItemStockQuantityException() {
		super();
	}

	public InvalidItemStockQuantityException(String message) {
		super(message);
	}

	public InvalidItemStockQuantityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidItemStockQuantityException(Throwable cause) {
		super(cause);
	}

	protected InvalidItemStockQuantityException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
