package com.project.doubleshop.domain.common.handlingexception.config;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.project.doubleshop.domain.common.handlingexception.ErrorResponseComposer;
import com.project.doubleshop.domain.common.handlingexception.advice.DefaultExceptionHandlerControllerAdvice;
import com.project.doubleshop.domain.common.handlingexception.hanlder.AbstractExceptionHandler;
import com.project.doubleshop.domain.common.handlingexception.util.SimpleMessageUtils;
import com.project.doubleshop.domain.common.handlingexception.validator.SimpleValidator;

@Configuration
@ComponentScan(basePackageClasses= AbstractExceptionHandler.class)
public class HandlingExceptionAutoConfiguration {

	private static final Log log = LogFactory.getLog(HandlingExceptionAutoConfiguration.class);

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	@ConditionalOnMissingBean(ErrorResponseComposer.class)
	public <T extends Throwable> ErrorResponseComposer<T> errorResponseComposer(
		List<AbstractExceptionHandler<T>> handlers) {
		log.info("Configuring ErrorResponseComposer");
		return new ErrorResponseComposer<T>(handlers);
	}

	@Bean
	@ConditionalOnMissingBean(DefaultExceptionHandlerControllerAdvice.class)
	public <T extends Throwable>
	DefaultExceptionHandlerControllerAdvice<T> defaultExceptionHandlerControllerAdvice(ErrorResponseComposer<T> errorResponseComposer) {

		log.info("Configuring DefaultExceptionHandlerControllerAdvice");
		return new DefaultExceptionHandlerControllerAdvice<T>(errorResponseComposer);
	}

	@Bean
	public SimpleMessageUtils simpleValidateUtils(MessageSource messageSource) {

		log.info("Configuring SimpleMessageUtils");
		return new SimpleMessageUtils(messageSource);
	}

	@Bean
	public SimpleValidator simpleValidator(LocalValidatorFactoryBean localValidatorFactoryBean) {

		log.info("Configuring simpleValidator");
		return new SimpleValidator(localValidatorFactoryBean);
	}
}
