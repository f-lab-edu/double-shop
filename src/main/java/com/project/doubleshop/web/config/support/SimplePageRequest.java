package com.project.doubleshop.web.config.support;

import static com.project.doubleshop.web.config.support.PageConst.*;

import lombok.Getter;

@Getter
public class SimplePageRequest implements Pageable {

	private final long page;

	private final int size;

	/**
	 * 리졸버에 기본 생성자로 확장할 시, 기본값이 들어간 페이징 관련 파라미터 값을 전달.
	 * DEFAULT_PAGE_NUMBER - 기본 페이지 번호(0)
	 * DEFAULT_MAX_SIZE - 기본 페이지당 최대 사이즈(9)
	 */
	public SimplePageRequest() {
		this(DEFAULT_PAGE_NUMBER, DEFAULT_MAX_SIZE);
	}

	public SimplePageRequest(long page, int size) {
		if(page < 0) {
			throw new IllegalArgumentException("Page must be greater or equals to zero.");
		}
		if(size < 1) {
			throw new IllegalArgumentException("Limit must be greater than zero.");
		}

		this.page = page;
		this.size = size;
	}

	@Override
	public long page() {
		return this.page;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public String toString() {
		return "SimplePageRequest{" +
			"page=" + page +
			", size=" + size +
			'}';
	}
}
