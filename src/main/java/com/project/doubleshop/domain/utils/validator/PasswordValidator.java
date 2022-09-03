package com.project.doubleshop.domain.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.project.doubleshop.domain.utils.annotation.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private int min;
	private int max;

	@Override
	public void initialize(Password constraintAnnotation) {
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if (!StringUtils.hasText(password))
			return sendViolationMsgAndReturnFalse(context, "Password must be filled.");
		if (!isValidLength(password))
			return sendViolationMsgAndReturnFalse(context, String.format("\n"
				+ "Password must be between %d and %d characters.", min, max));
		return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{" + min
			+ "," + max + "}$");
	}

	private boolean isValidLength(String password) {
		int length = password.length();
		return length < min || length > max;
	}

	private boolean sendViolationMsgAndReturnFalse(ConstraintValidatorContext context, String msg) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(msg)
			.addConstraintViolation();
		return false;
	}
}
