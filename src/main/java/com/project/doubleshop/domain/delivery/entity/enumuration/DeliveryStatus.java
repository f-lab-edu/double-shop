package com.project.doubleshop.domain.delivery.entity.enumuration;

import static com.project.doubleshop.domain.delivery.entity.enumuration.DeliveryStatusConstant.*;

public enum DeliveryStatus {
	PRODUCT_PREPARATION(PRODUCT_PREPARATION_CODE),
	DELIVERY_PREPARATION(DELIVERY_PREPARATION_CODE),
	DELIVERY_ON_HOLD(DELIVERY_ON_HOLD_CODE),
	DELIVERY_ONGOING(DELIVERY_ONGOING_CODE),
	DELIVERY_COMPLETE(DELIVERY_COMPLETE_CODE);

	private final int i;

	DeliveryStatus(int i) {
		this.i = i;
	}

	public static DeliveryStatus of(int value) {
		switch (value) {
			case PRODUCT_PREPARATION_CODE:
				return DeliveryStatus.PRODUCT_PREPARATION;
			case DELIVERY_PREPARATION_CODE:
				return DeliveryStatus.DELIVERY_PREPARATION;
			case DELIVERY_ON_HOLD_CODE:
				return DeliveryStatus.DELIVERY_ON_HOLD;
			case DELIVERY_ONGOING_CODE:
				return DeliveryStatus.DELIVERY_ONGOING;
			case DELIVERY_COMPLETE_CODE:
				return DeliveryStatus.DELIVERY_COMPLETE;
			default:
				throw new AssertionError(String.format("Unknown DeliveryStatus value : %d", value));
		}
	}

	public int getValue() {
		return i;
	}
}
