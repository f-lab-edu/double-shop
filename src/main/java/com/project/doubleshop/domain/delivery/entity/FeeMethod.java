package com.project.doubleshop.domain.delivery.entity;

import static com.project.doubleshop.domain.delivery.entity.FeeMethodConstant.*;

public enum FeeMethod {
	PREPAID(PREPAID_CODE),
	POSTPAID(POSTPAID_CODE);

	private final int i;

	FeeMethod(int i) {
		this.i = i;
	}

	public static FeeMethod of(int value) {
		switch (value) {
			case PREPAID_CODE:
				return FeeMethod.PREPAID;
			case POSTPAID_CODE:
				return FeeMethod.POSTPAID;
			default:
				throw new AssertionError(String.format("Unknown FeeMethod value : %d", value));
		}
	}

	public static FeeMethod of(String value) {
		for (FeeMethod feeMethod : FeeMethod.values()) {
			if (feeMethod.name().equalsIgnoreCase(value)) {
				return feeMethod;
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
