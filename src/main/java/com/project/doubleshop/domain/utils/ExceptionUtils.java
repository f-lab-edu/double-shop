package com.project.doubleshop.domain.utils;

import java.util.List;

import com.project.doubleshop.domain.exception.BadRequestException;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.exception.ServiceException;

public class ExceptionUtils {

	public static void findInvalidIdsAndThrow400Error(List<Long> invalidIds, String message) {
		throw new BadRequestException(generateExceptionMessage(invalidIds, message));
	}

	public static void findInvalidIdsAndThrow404Error(List<Long> invalidIds, String message) {
		throw new NotFoundException(generateExceptionMessage(invalidIds, message));
	}

	private static String generateExceptionMessage(List<Long> invalidIds, String message) {
		StringBuilder sb = new StringBuilder();

		sb.append(message);
		sb.append(" [");
		for (int i = 0; i < invalidIds.size(); i++) {
			sb.append(invalidIds.get(i));
			if (i < invalidIds.size() - 1) {
				sb.append(", ");
			} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}
}
