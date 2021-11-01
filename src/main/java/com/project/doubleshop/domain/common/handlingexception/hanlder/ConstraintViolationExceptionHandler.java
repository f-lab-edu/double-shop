package com.project.doubleshop.domain.common.handlingexception.hanlder;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.common.handlingexception.SimpleFieldError;
import com.project.doubleshop.domain.common.handlingexception.util.SimpleMessageUtils;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ConstraintViolationExceptionHandler<E extends ConstraintViolationException> extends AbstractExceptionHandler<E> {

	public ConstraintViolationExceptionHandler() {

		super(ConstraintViolationException.class.getSimpleName());
		log.info("Created");
	}

	public ConstraintViolationExceptionHandler(String exceptionName) {
		super(exceptionName);
	}

	@Override
	protected HttpStatus getStatus(E exception) {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	protected Collection<SimpleFieldError> getErrors(E exception) {
		return SimpleFieldError.getErrors(exception.getConstraintViolations());
	}

	@Override
	protected String getMessage(E exception) {
		return SimpleMessageUtils.getMessage("io.github.wannidev.validationError");
	}
}
