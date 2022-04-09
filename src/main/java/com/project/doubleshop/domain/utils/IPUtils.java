package com.project.doubleshop.domain.utils;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPUtils {
	public static String getClientIpAddress(HttpServletRequest request) {
		String xForwardedForHeader = request.getHeader("X-Forwarded-For");
		if (xForwardedForHeader == null) {
			return request.getRemoteAddr();
		} else {
			// https://en.wikipedia.org/wiki/X-Forwarded-For
			// general format of the 'X-Forwarded-For' field: client, proxy1, proxy2 ...
			return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
		}
	}
}
