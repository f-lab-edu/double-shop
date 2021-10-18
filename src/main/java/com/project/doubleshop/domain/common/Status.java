package com.project.doubleshop.domain.common;

import static com.project.doubleshop.domain.common.StatusConstant.*;

public enum Status {
	ACTIVATED(ACTIVATED_CODE),
	SUSPENDED(SUSPENDED_CODE),
	TO_BE_DELETED(TO_BE_DELETED_CODE);

	private final int i;

	Status(int i) {
		this.i = i;
	}

	public static Status valueOf(int value) {
		switch (value) {
			case ACTIVATED_CODE:
				return Status.ACTIVATED;
			case SUSPENDED_CODE:
				return Status.SUSPENDED;
			case TO_BE_DELETED_CODE:
				return Status.TO_BE_DELETED;
			default:
				throw new AssertionError(String.format("unknown Status value : %d", value));
		}
	}

	public static Status of(String value) {
		for (Status status : Status.values()) {
			if (status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		return null;
	}

	public int getValue() {
		return i;
	}

	@Override
	public String toString() {
		return this.name();
	}

}
