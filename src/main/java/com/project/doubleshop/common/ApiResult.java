package com.project.doubleshop.common;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApiResult<T> {
	private final boolean success;
	private final T response;
	private final ApiError error;

	public static <T> ApiResult<T> OK(T response) {
		return new ApiResult<>(true, response, null);
	}

	public static ApiResult<?> ERROR(Throwable throwable, HttpStatus status) {
		return new ApiResult<>(false, null, new ApiError(throwable, status));
	}
}
