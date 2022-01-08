package com.project.doubleshop.domain.delivery.service;

import java.util.List;

import com.project.doubleshop.domain.order.entity.mock.OrderItem;

public interface DeliveryProcessManagement<T, S> {

	void doProcess();

	List<T> doPreparation();

	S statusPreparation();

	S statusHold();
}
