package com.project.doubleshop.web.item.exhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class HttpErrorResult {
	private final String statusCode;
	private final String message;
}
