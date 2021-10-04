package com.project.doubleshop.web.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.doubleshop.web.config.support.SimpleOffsetPageRequest;
import com.project.doubleshop.web.config.support.SimpleOffsetPageableHandlerMethodResolver;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

	@Bean
	public SimpleOffsetPageableHandlerMethodResolver simpleOffsetPageableHandlerMethodResolver() {
		SimpleOffsetPageableHandlerMethodResolver resolver = new SimpleOffsetPageableHandlerMethodResolver();
		resolver.setSimpleOffsetPageRequest(new SimpleOffsetPageRequest(0, 9));
		return resolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(simpleOffsetPageableHandlerMethodResolver());
	}
}
