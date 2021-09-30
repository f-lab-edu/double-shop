package com.project.doubleshop.domain.common;

public enum Status {
	ACTIVATED,
	SUSPENDED,
	DELETED;

	@Override
	public String toString() {
		return this.name();
	}
}
