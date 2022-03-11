package com.project.doubleshop.web.order.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.address.entity.Address;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderApiResult {

	private final Long id;

	private final LocalDateTime orderedTime;

	private final Integer orderStatus;

	private final Integer orderType;

	private final Integer totalPrice;

	private final Address address;

	public OrderApiResult(Order order) {
		this.id = order.getId();
		this.orderedTime = order.getOrderedTime();
		this.orderStatus = order.getOrderStatus();
		this.orderType = order.getOrderType();
		this.totalPrice = order.getTotalPrice();
		this.address = order.getAddress();
	}
}
