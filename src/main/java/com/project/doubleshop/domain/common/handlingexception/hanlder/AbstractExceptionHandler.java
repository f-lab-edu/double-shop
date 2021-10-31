package com.project.doubleshop.domain.common.handlingexception.hanlder;

import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.project.doubleshop.domain.common.handlingexception.ErrorResponse;
import com.project.doubleshop.domain.common.handlingexception.SimpleFieldError;

public abstract class AbstractExceptionHandler<T extends Throwable> {

	private String exceptionName;

	public AbstractExceptionHandler(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	protected String getMessage(T exception) {
		return exception.getMessage();
	}

	protected HttpStatus getStatus(T exception) {
		return null;
	}

	protected Collection<SimpleFieldError> getErrors(T exception) {
		return null;
	}

	public ErrorResponse getErrorResponse(T exception) {

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setMessage(getMessage(exception));

		HttpStatus status = getStatus(exception);

		if (status != null) {
			errorResponse.setStatus(status.value());
			errorResponse.setError(status.getReasonPhrase());
		}

		errorResponse.setFieldErrors(getErrors(exception));

		return errorResponse;
	}
}
