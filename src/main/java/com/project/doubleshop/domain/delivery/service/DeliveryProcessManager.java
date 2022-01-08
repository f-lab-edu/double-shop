package com.project.doubleshop.domain.delivery.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.order.entity.mock.Order;
import com.project.doubleshop.domain.order.entity.mock.OrderItem;
import com.project.doubleshop.domain.order.repository.mock.OrderItemRepository;
import com.project.doubleshop.domain.order.repository.mock.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryProcessManager implements DeliveryProcessManagement<Delivery, DeliveryStatus> {

	private final ItemRepository itemRepository;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	public void doProcess() {
		// 상품 준비 단계
		List<Delivery> deliveries = doPreparation();

	}

	@Override
	public List<Delivery> doPreparation() {
		List<Delivery> result = new LinkedList<>();

		// 상품 준비 단계
		List<Order> orders = orderRepository.findAll();

		List<Long> orderIds = orders
			.stream()
			.filter(Order::isCanceled)
			.map(Order::getId)
			.collect(Collectors.toList());

		List<OrderItem> orderItems = orderItemRepository.findOrdersByOrderIds(orderIds);

		// 주문 상품의 상품 검색
		List<Long> itemIds = orderItems
			.stream()
			.map(OrderItem::getItemId)
			.collect(Collectors.toList());

		List<Item> items = itemRepository.findItemsByOrderIds(itemIds);

		// 검색한 상품에서 유효하지 않은 상품 pk 수집
		Set<Long> invalidItemIds = items
			.stream()
			.filter(i -> i.getStatus().equals(Status.TO_BE_DELETED) || i.getStatus().equals(Status.SUSPENDED))
			.map(Item::getId)
			.collect(Collectors.toSet());

		// 주문 상품에서 유효하지 않은 상품이 포함된, 주문 id 수집
		Set<Long> invalidOrderId = orderItems
			.stream()
			.filter(oi -> invalidItemIds.contains(oi.getItemId()))
			.map(OrderItem::getOrderId)
			.collect(Collectors.toSet());

		LocalDateTime now = LocalDateTime.now();
		orderIds.forEach(id -> {
			DeliveryStatus deliveryStatus = invalidOrderId.contains(id) ? statusHold() :  statusPreparation();
			result.add(
				Delivery.builder()
					.orderId(id)
					.createTime(now)
					.deliveryStatus(deliveryStatus)
					.status(Status.ACTIVATED)
					.build()
			);
		});

		return result;
	}

	@Override
	public DeliveryStatus statusPreparation() {
		return DeliveryStatus.DELIVERY_PREPARATION;
	}

	@Override
	public DeliveryStatus statusHold() {
		return DeliveryStatus.DELIVERY_ON_HOLD;
	}
}
