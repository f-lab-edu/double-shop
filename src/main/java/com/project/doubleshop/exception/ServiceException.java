package com.project.doubleshop.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ServiceException extends RuntimeException {
	private LocalDateTime timeStamp;
	protected Integer statusCode;

	public ServiceException(String message) {
		super(message);
		this.timeStamp = LocalDateTime.now();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
	}

	public ServiceException(String message, Integer statusCode) {
		super(message);
		this.timeStamp = LocalDateTime.now();
		this.statusCode = statusCode;
	}
}
