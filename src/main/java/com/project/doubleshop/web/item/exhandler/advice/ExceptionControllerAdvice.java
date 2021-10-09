package com.project.doubleshop.web.item.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.doubleshop.web.item.exception.InvalidArgumentException;
import com.project.doubleshop.web.item.exception.DataNotFoundException;
import com.project.doubleshop.web.item.exhandler.HttpErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	public HttpErrorResult notFoundExceptionHandler(DataNotFoundException e) {
		log.error("[itemExceptionHandler] ex", e);
		return new HttpErrorResult(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidArgumentException.class)
	public HttpErrorResult badRequestHandler(InvalidArgumentException e) {
		log.error("[badRequestHandler] ex", e);
		return new HttpErrorResult(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
	}
}
