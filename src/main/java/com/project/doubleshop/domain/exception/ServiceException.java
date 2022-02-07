package com.project.doubleshop.domain.exception;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ServiceException extends RuntimeException {
	private LocalDateTime timeStamp;
	private Integer status;

	public ServiceException(String message, Integer status) {
		super(message);
		this.timeStamp = LocalDateTime.now();
		this.status = status;
	}
}
