package com.project.doubleshop.web.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application-token.yml")
@ConfigurationProperties(prefix = "token")
public class SimpleTokenConfigure {

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
