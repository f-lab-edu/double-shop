package com.project.doubleshop.web.config.support;

import static java.lang.Math.*;
import static org.springframework.util.StringUtils.*;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimpleOffsetPageableHandlerMethodResolver implements HandlerMethodArgumentResolver {

	private static final String DEFAULT_OFFSET_PARAMETER = "page";

	private static final String DEFAULT_LIMIT_PARAMETER = "limit";

	private static final int DEFAULT_LIMIT_MAX_SIZE = 9;

	private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;

	private String limitParameterName = DEFAULT_LIMIT_PARAMETER;

	private SimpleOffsetPageRequest simpleOffsetPageRequest;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Pageable.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		String offsetString = webRequest.getParameter(offsetParameterName);
		String limitString = webRequest.getParameter(limitParameterName);

		boolean isPageAndLimitGiven = hasText(offsetString) && hasText(limitString);

		if(!isPageAndLimitGiven && simpleOffsetPageRequest == null) {
			return null;
		}

		int limit = hasText(limitString) ? parseAndApplyBoundaries(limitString, DEFAULT_LIMIT_MAX_SIZE) : simpleOffsetPageRequest.limit();
		long page = hasText(offsetString) ? parseAndApplyBoundaries(offsetString, Integer.MAX_VALUE) : simpleOffsetPageRequest.page();

		limit = limit < 1 ? simpleOffsetPageRequest.limit() : limit;
		limit = min(limit, DEFAULT_LIMIT_MAX_SIZE);

		page = page * limit;

		return new SimpleOffsetPageRequest(page, limit);
	}

	private int parseAndApplyBoundaries(String parameter, int upper) {
		int parsed = toInt(parameter, 0);
		return parsed < 0 ? 0 : min(parsed, upper);
	}

	public void setSimpleOffsetPageRequest(SimpleOffsetPageRequest simpleOffsetPageRequest) {
		this.simpleOffsetPageRequest = simpleOffsetPageRequest;
	}

	public void setOffsetParameterName(String offsetParameterName) {
		this.offsetParameterName = offsetParameterName;
	}

	public void setLimitParameterName(String limitParameterName) {
		this.limitParameterName = limitParameterName;
	}

	private int toInt(String str, int defaultValue) {
		if (str == null) {
			return defaultValue;
		} else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
	}
}
