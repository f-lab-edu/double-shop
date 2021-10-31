package com.project.doubleshop.domain.common.handlingexception.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class SimpleMessageUtils {

	private static final Log log = LogFactory.getLog(SimpleMessageUtils.class);

	private static MessageSource messageSource;

	public SimpleMessageUtils(MessageSource messageSource) {

		SimpleMessageUtils.messageSource = messageSource;

		log.info("Created");
	}

	public static String getMessage(String messageKey, Object... args) {

		if (messageSource == null)
			return "ApplicationContext unavailable";

		// http://stackoverflow.com/questions/10792551/how-to-obtain-a-current-user-locale-from-spring-without-passing-it-as-a-paramete
		return messageSource.getMessage(messageKey, args,
			LocaleContextHolder.getLocale());
	}
}
