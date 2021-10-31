package com.project.doubleshop.domain.common.handlingexception;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import com.project.doubleshop.domain.common.handlingexception.hanlder.AbstractExceptionHandler;

public class ErrorResponseComposer<T extends Throwable> {

	private static final Log log = LogFactory.getLog(ErrorResponseComposer.class);

	private final Map<String, AbstractExceptionHandler<T>> handlers;

	public ErrorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {

		this.handlers = handlers.stream().collect(
			Collectors.toMap(AbstractExceptionHandler::getExceptionName,
				Function.identity(), (handler1, handler2) -> AnnotationAwareOrderComparator
					.INSTANCE.compare(handler1, handler2) < 0 ?
					handler1 : handler2));

		log.info("Created");
	}

	/**
	 * 예외가 주어지면, 핸들러를 찾는다.
	 * 핸들러를 통해 응답 메세지를 빌드하고, 리턴한다.
	 * @param exception 서비스 로직에 의해 발생한 예외.
	 * @return errorResponse
	 */
	public Optional<ErrorResponse> compose(T exception) {

		AbstractExceptionHandler<T> handler = null;

		// 예외를 다루는 핸들러는 조회하는데, 핸들러를 못찾으면,
		// exception.getCause()를 통해 그 예외 자체를 할당한다.
		while (exception != null) {

			handler = handlers.get(exception.getClass().getSimpleName());

			if (handler != null) {
				break;
			}

			if (RuntimeException.class.isAssignableFrom(exception.getClass())) {
				handler = handlers.get(RuntimeException.class.getSimpleName());
				break;
			}

			exception = (T) exception.getCause();
		}

		if (handler != null) {
			return Optional.of(handler.getErrorResponse(exception));
		}

		return Optional.empty();
	}
}
