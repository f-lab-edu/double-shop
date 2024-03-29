package com.project.doubleshop.web.common.exhandler.dto;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.doubleshop.exception.ServiceException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ErrorResponse {
	private LocalDateTime timeStamp;
	private Integer statusCode;
	private String message;
	private String path;

	public static <T extends ServiceException> ErrorResponse configure(T exception) {
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.build()
			.toUri();

		return ErrorResponse.builder()
			.timeStamp(LocalDateTime.now())
			.statusCode(exception.getStatusCode())
			.message(exception.getMessage())
			.path(location.getPath())
			.build();
	}

	public boolean inComplete() {
		return timeStamp == null || statusCode == null || message == null || path == null;
	}
}