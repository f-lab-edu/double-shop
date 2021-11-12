package com.project.doubleshop.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.web.item.exhandler.HttpErrorResult;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException e) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(objectMapper.writeValueAsString(
			new HttpErrorResult(HttpStatus.UNAUTHORIZED.getReasonPhrase(),
				"Authentication error (cause: unauthorized)")));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
