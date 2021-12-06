package com.project.doubleshop.web.config.support;

import static com.project.doubleshop.web.config.support.PageConst.*;
import static java.lang.Math.*;
import static org.springframework.util.StringUtils.*;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.project.doubleshop.web.item.exception.InvalidParameterValueException;

public class SimpleOffsetPageableHandlerMethodResolver implements HandlerMethodArgumentResolver {

	private SimplePageRequest simplePageRequest;

	private String pageParameterName = DEFAULT_PAGE_PARAMETER;

	private String sizeParameterName = DEFAULT_SIZE_PARAMETER;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Pageable.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		String pageString = webRequest.getParameter(pageParameterName);
		String sizeString = webRequest.getParameter(sizeParameterName);

		boolean hasPageString = hasText(pageString);
		boolean hasSizeString = hasText(sizeString);

		boolean isPageAndLimitGiven = hasPageString && hasSizeString;

		if (!isPageAndLimitGiven && simplePageRequest == null) {
			return null;
		}

		int size = hasSizeString ? parseAndApplyBoundaries(sizeString, DEFAULT_MAX_SIZE) : simplePageRequest.size();
		long page = hasPageString ? parseAndApplyBoundaries(pageString, Integer.MAX_VALUE) : simplePageRequest.page();

		size = size < 1 ? simplePageRequest.size() : size;
		size = min(size, DEFAULT_MAX_SIZE);

		page = page * size;

		return new SimplePageRequest(page, size);
	}

	private int parseAndApplyBoundaries(String parameter, int upper) {
		int parsed = toInt(parameter, 0);
		return parsed < 0 ? 0 : min(parsed, upper);
	}

	public void setSimpleOffsetPageRequest(SimplePageRequest simplePageRequest) {
		this.simplePageRequest = simplePageRequest;
	}

	public void setPageParameterName(String offsetParameterName) {
		this.pageParameterName = offsetParameterName;
	}

	public void setSizeParameterName(String sizeParameterName) {
		this.sizeParameterName = sizeParameterName;
	}

	private int toInt(String str, int defaultValue) {
		if (str == null) {
			return defaultValue;
		} else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				throw new InvalidParameterValueException("Parameter value must be integer type.");
			}
		}
	}
}
