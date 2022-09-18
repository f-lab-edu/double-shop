package com.project.doubleshop.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException {
	public BadRequestException(String message) {
		super(message);
		this.statusCode = HttpStatus.BAD_REQUEST.value();
	}
}
