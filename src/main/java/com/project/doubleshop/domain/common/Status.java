package com.project.doubleshop.domain.common;

public enum Status {
	ACTIVATED,
	SUSPENDED,
	TO_BE_DELETED;

	public static Status of(String value) {
		for (Status status : Status.values()) {
			if (status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name();
	}
}
