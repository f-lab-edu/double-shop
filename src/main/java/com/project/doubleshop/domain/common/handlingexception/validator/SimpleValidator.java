package com.project.doubleshop.domain.common.handlingexception.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.project.doubleshop.domain.common.handlingexception.exception.ExplicitConstraintViolationException;

public class SimpleValidator {

	private static final Log log = LogFactory.getLog(SimpleValidator.class);

	private final LocalValidatorFactoryBean validator;

	public SimpleValidator(LocalValidatorFactoryBean validator) {

		this.validator = validator;

		log.info("Created");
	}

	public <T> void validate(String name, T object, Class<?>... groups) {

		Set<ConstraintViolation<T>> violations = validator.validate(object);

		if(!violations.isEmpty()) {
			throw new ExplicitConstraintViolationException(violations, name);
		}
	}

	public <T,E> void validate(String name, T object, Throwable exception) throws Throwable {
		throw exception;
	}
}
