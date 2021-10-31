package com.project.doubleshop.domain.common.handlingexception.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ExplicitConstraintViolationException extends ConstraintViolationException {

	private final String objectName;

	public ExplicitConstraintViolationException(
		Set<? extends ConstraintViolation<?>> constraintViolations, String objectName) {
		super(constraintViolations);
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}
}
