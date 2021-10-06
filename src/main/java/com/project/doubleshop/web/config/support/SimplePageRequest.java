package com.project.doubleshop.web.config.support;

import lombok.Getter;

@Getter
public class SimpleOffsetPageRequest implements Pageable {

	private long page;

	private int limit;

	public SimpleOffsetPageRequest() {
		this(0, 9);
	}

	public SimpleOffsetPageRequest(long page, int limit) {
		if(page < 0) {
			throw new IllegalArgumentException("Page must be greater or equals to zero.");
		}
		if(limit < 1) {
			throw new IllegalArgumentException("Limit must be greater than zero.");
		}

		this.page = page;
		this.limit = limit;
	}

	@Override
	public long page() {
		return this.page;
	}

	@Override
	public int limit() {
		return this.limit;
	}

	@Override
	public String toString() {
		return "SimpleOffsetPageRequest{" +
			"offset=" + page +
			", limit=" + limit +
			'}';
	}
}
