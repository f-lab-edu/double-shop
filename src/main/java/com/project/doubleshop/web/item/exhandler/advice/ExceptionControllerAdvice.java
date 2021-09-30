package com.project.doubleshop.web.item.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.doubleshop.web.item.exception.ItemNotFoundException;
import com.project.doubleshop.web.item.exhandler.HttpErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ItemNotFoundException.class)
	public HttpErrorResult itemExceptionHandler(ItemNotFoundException e) {
		log.error("[itemExceptionHandler] ex", e);
		return new HttpErrorResult(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public HttpErrorResult badRequestHandler(IllegalArgumentException e) {
		log.error("[badRequestHandler] ex", e);
		return new HttpErrorResult(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
	}
}
