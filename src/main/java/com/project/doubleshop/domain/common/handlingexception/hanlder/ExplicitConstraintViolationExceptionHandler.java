package com.project.doubleshop.domain.common.handlingexception.hanlder;

import java.util.Collection;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.common.handlingexception.SimpleFieldError;
import com.project.doubleshop.domain.common.handlingexception.exception.ExplicitConstraintViolationException;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExplicitConstraintViolationExceptionHandler extends ConstraintViolationExceptionHandler<ExplicitConstraintViolationException> {

	public ExplicitConstraintViolationExceptionHandler() {

		super(ExplicitConstraintViolationException.class.getSimpleName());
		log.info("Created");
	}

	@Override
	public Collection<SimpleFieldError> getErrors(ExplicitConstraintViolationException exception) {
		return SimpleFieldError.getErrors(exception);
	}
}
