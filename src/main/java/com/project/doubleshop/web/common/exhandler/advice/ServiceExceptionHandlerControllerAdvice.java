package com.project.doubleshop.web.common.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.doubleshop.domain.exception.ServiceException;
import com.project.doubleshop.web.common.exhandler.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandlerControllerAdvice<T extends ServiceException> {

	@RequestMapping(produces = "application/json")
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> handlingException(T exception) throws T {
		ErrorResponse errorResponse = ErrorResponse.configure(exception);

		log.warn("Unexpected exception occurred", exception);

		if (errorResponse.inComplete()) {
			throw exception;
		}
		
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
	}
}
