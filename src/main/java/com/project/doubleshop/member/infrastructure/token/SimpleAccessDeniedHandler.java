package com.project.doubleshop.member.infrastructure.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doubleshop.exception.NotAuthorizedException;
import com.project.doubleshop.web.common.exhandler.dto.ErrorResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException e) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(objectMapper.writeValueAsString(objectMapper.writeValueAsString(
			ErrorResponse.configure(
				new NotAuthorizedException("Authentication error (cause: forbidden)", HttpStatus.FORBIDDEN.value())
			)
		)));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
