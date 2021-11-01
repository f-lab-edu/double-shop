package com.project.doubleshop.domain.common.handlingexception.hanlder;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

/**
 * 400 응답 처리가 필요한 핸들러의 경우에 사용되는 Exception Handler
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public abstract class AbstractBadRequestExceptionHandler<T extends Throwable> extends AbstractExceptionHandler<T> {

	public AbstractBadRequestExceptionHandler(String exceptionName) {
		super(exceptionName);
	}

	@Override
	public HttpStatus getStatus(T ex) {
		return HttpStatus.BAD_REQUEST;
	}
}
