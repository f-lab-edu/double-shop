package com.project.doubleshop.domain.order.entity.mock;

import static com.project.doubleshop.domain.delivery.entity.DeliveryStatusConstant.*;
import static com.project.doubleshop.domain.order.entity.mock.OrderTypeConstant.*;

import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;

public enum OrderType {
	FRESH(FRESH_CODE),
	ONE_DAY(ONE_DAY_CODE),
	NORMAL(NORMAL_CODE);

	private final int i;

	OrderType(int i) {
		this.i = i;
	}

	public static OrderType of(int value) {
		switch (value) {
			case FRESH_CODE:
				return OrderType.FRESH;
			case ONE_DAY_CODE:
				return OrderType.ONE_DAY;
			case NORMAL_CODE:
				return OrderType.NORMAL;
			default:
				throw new AssertionError(String.format("Unknown OrderType value : %d", value));
		}
	}

	public int getValue() {
		return i;
	}
}
