package com.project.doubleshop.web.config.support;

import lombok.Getter;

@Getter
public class SimpleOffsetPageRequest implements Pageable {

	private int offset;

	private int limit;

	public SimpleOffsetPageRequest() {
		this(0, 9);
	}

	public SimpleOffsetPageRequest(int offset, int limit) {
		if(offset < 0) {
			throw new IllegalArgumentException("Offset must be greater or equals to zero.");
		}
		if(limit < 1) {
			throw new IllegalArgumentException("Limit must be greater than zero.");
		}

		this.offset = offset;
		this.limit = limit;
	}

	@Override
	public int offset() {
		return this.offset;
	}

	@Override
	public int limit() {
		return this.limit;
	}

	@Override
	public String toString() {
		return "SimpleOffsetPageRequest{" +
			"offset=" + offset +
			", limit=" + limit +
			'}';
	}
}
