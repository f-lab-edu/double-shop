package com.project.doubleshop.domain.common.handlingexception.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.doubleshop.domain.common.handlingexception.ErrorResponse;
import com.project.doubleshop.domain.common.handlingexception.ErrorResponseComposer;

@RestControllerAdvice
public class DefaultExceptionHandlerControllerAdvice<T extends Throwable> {

	private static final Log log = LogFactory.getLog(DefaultExceptionHandlerControllerAdvice.class);

	private final ErrorResponseComposer<T> errorResponseComposer;

	public DefaultExceptionHandlerControllerAdvice(
		ErrorResponseComposer<T> errorResponseComposer) {
		this.errorResponseComposer = errorResponseComposer;
	}

	@RequestMapping(produces = "application/json")
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handlingException(T exception) throws T {
		ErrorResponse errorResponse = errorResponseComposer.compose(exception).orElseThrow(() -> exception);

		if (errorResponse.incomplete()) {
			throw exception;
		}

		log.warn("Handling exception", exception);

		errorResponse.setException(exception.getClass().getSimpleName());
		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
	}
}
