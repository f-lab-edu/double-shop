package com.project.doubleshop.web.config;

import static com.project.doubleshop.web.config.support.PageConst.*;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.doubleshop.web.config.support.PageConst;
import com.project.doubleshop.web.config.support.SimplePageRequest;
import com.project.doubleshop.web.config.support.SimpleOffsetPageableHandlerMethodResolver;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

	@Bean
	public SimpleOffsetPageableHandlerMethodResolver simpleOffsetPageableHandlerMethodResolver() {
		SimpleOffsetPageableHandlerMethodResolver resolver = new SimpleOffsetPageableHandlerMethodResolver();
		resolver.setSimpleOffsetPageRequest(new SimplePageRequest(DEFAULT_PAGE_NUMBER, DEFAULT_MAX_SIZE));
		return resolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(simpleOffsetPageableHandlerMethodResolver());
	}
}
