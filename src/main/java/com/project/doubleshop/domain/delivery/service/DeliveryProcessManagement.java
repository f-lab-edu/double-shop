package com.project.doubleshop.domain.delivery.service;

import java.util.List;

import com.project.doubleshop.domain.order.entity.mock.OrderItem;

public interface DeliveryProcessManagement {

	void doProcess(List<OrderItem> orderItems);

	Boolean preparation();

	void hold();

	void ready();

	void start();

	void onGoing();

	void complete();
}
