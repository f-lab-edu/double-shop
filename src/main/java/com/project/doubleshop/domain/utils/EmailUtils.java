package com.project.doubleshop.domain.utils;

import static java.util.regex.Pattern.*;
import static org.springframework.util.StringUtils.*;

public class EmailUtils {

	private static boolean checkIsBlank(String email) {
		if (hasText(email)) {
			return true;
		}
		throw new IllegalArgumentException("Email must be provided.");
	}

	private static boolean checkFormat(String email) {
		if (matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", email)) {
			return true;
		}
		throw new IllegalArgumentException("Wrong email format detected");
	}

	public static boolean checkEmail(String email) {
		return checkIsBlank(email) && checkFormat(email);
	}
}
