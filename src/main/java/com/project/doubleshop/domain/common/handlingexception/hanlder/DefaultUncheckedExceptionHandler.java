package com.project.doubleshop.domain.common.handlingexception.hanlder;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.project.doubleshop.domain.common.handlingexception.util.SimpleMessageUtils;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultUncheckedExceptionHandler<E extends RuntimeException> extends AbstractExceptionHandler<E> {

	public DefaultUncheckedExceptionHandler() {

		super(RuntimeException.class.getSimpleName());
		log.info("Created");
	}

	public DefaultUncheckedExceptionHandler(String exceptionName) {
		super(exceptionName);
	}

	@Override
	public HttpStatus getStatus(E ex) {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getMessage(E ex) {
		return SimpleMessageUtils.getMessage("io.github.wannidev.defaultRuntimeException", ex.getMessage());
	}
}
