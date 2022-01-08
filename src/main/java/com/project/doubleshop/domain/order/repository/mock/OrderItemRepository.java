package com.project.doubleshop.domain.order.repository.mock;

import java.util.List;

import com.project.doubleshop.domain.order.entity.mock.OrderItem;

public interface OrderItemRepository {

	boolean save(OrderItem order);

	OrderItem findById(Long id);

	List<OrderItem> findAll();

	List<OrderItem> findOrderItemsByOrderIds(List<Long> ids);
}
