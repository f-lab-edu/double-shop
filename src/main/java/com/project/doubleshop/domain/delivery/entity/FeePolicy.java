package com.project.doubleshop.domain.delivery.entity;

import static com.project.doubleshop.domain.delivery.entity.FeePolicyConstant.*;

public enum FeePolicy {
	FREE(FREE_CODE),
	ONCE(ONCE_CODE),
	ON_CONDITION(ON_CONDITION_CODE);

	private final int i;

	FeePolicy(int i) {
		this.i = i;
	}

	public static FeePolicy of(int value) {
		switch (value) {
			case FREE_CODE:
				return FeePolicy.FREE;
			case ONCE_CODE:
				return FeePolicy.ONCE;
			case ON_CONDITION_CODE:
				return FeePolicy.ON_CONDITION;
			default:
				throw new AssertionError(String.format("Unknown FeePolicy value : %d", value));
		}
	}

	public static FeePolicy of(String value) {
		for (FeePolicy feePolicy : FeePolicy.values()) {
			if (feePolicy.name().equalsIgnoreCase(value)) {
				return feePolicy;
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
