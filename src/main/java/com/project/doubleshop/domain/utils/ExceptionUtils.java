package com.project.doubleshop.domain.utils;

import java.util.List;

public class ExceptionUtils {
	public static void findInvalidIdsAndThrowException(List<Long> invalidIds, String message) {
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

		throw new IllegalArgumentException(sb.toString());
	}
}
