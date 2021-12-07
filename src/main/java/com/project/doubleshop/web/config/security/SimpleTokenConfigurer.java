package com.project.doubleshop.web.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
public class SimpleTokenConfigurer {

	private String header;

	private int expirySeconds;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getExpirySeconds() {
		return expirySeconds;
	}

	public void setExpirySeconds(int expirySeconds) {
		this.expirySeconds = expirySeconds;
	}
}
