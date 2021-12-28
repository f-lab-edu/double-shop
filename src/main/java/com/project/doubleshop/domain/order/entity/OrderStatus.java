package com.project.doubleshop.domain.order.entity;

import static com.project.doubleshop.domain.order.entity.OrderStatusConstants.CANCELED_CODE;
import static com.project.doubleshop.domain.order.entity.OrderStatusConstants.ORDERED_CODE;

public enum OrderStatus {
    ORDERED(ORDERED_CODE),
    CANCELED(CANCELED_CODE);

    private final int i;

    OrderStatus(int i) {
        this.i = i;
    }

    public static OrderStatus of(int value) {
        switch (value) {
            case ORDERED_CODE:
                return OrderStatus.ORDERED;
            case CANCELED_CODE:
                return OrderStatus.CANCELED;
            default:
                throw new AssertionError(String.format("Unknown OrderStatus value : %d", value));
        }
    }

    public int getValue() {
        return i;
    }
}
