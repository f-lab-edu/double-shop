package com.project.doubleshop.domain.delivery.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.domain.delivery.repository.DeliveryDriverRepository;
import com.project.doubleshop.domain.delivery.repository.DeliveryRepository;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.repository.ItemRepository;
import com.project.doubleshop.domain.order.entity.mock.Order;
import com.project.doubleshop.domain.order.entity.mock.OrderItem;
import com.project.doubleshop.domain.order.repository.mock.OrderItemRepository;
import com.project.doubleshop.domain.order.repository.mock.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryProcessManager implements DeliveryProcessManagement<Delivery, DeliveryStatus> {

	private final ItemRepository itemRepository;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final DeliveryRepository deliveryRepository;
	private final DeliveryDriverRepository deliveryDriverRepository;

	@Override
	public void doProcess(String phase) {
		List<Delivery> deliveries;
		switch (phase) {
			case "0":
				// 배송 프로세스 시작 -> 상품 준비 || 배송 보류
				deliveries = doProductsPreparation();
				break;
			case "1":
				// 상품 준비 -> 배송 준비 || 배송 보류
				deliveries = doDeliveriesPreparation();
				break;
			case "2":
				// 배송 준비 -> 배송 시작(요청)
				deliveries = doDeliveryBegin();
				break;
			case "3":
				// 배송 시작 -> 배송 중
				break;
			case "4":
				// 배송 중 -> 배송 완료 || 배송 보류
				break;
		}
	}
	
	private List<Delivery> doDeliveryBegin() {

		// 있다면, 주문 상품의 우선순위에 따라, 재배열
		List<Long> orderIds = new ArrayList<>();
		List<Long> deliveryIds = new ArrayList<>();

		// 배송 상태를 업데이트할 배송의 pk와 주문 fk 수집.
		toOngoingDeliveries.forEach(d -> {
			orderIds.add(d.getOrderId());
			deliveryIds.add(d.getId());
		});

		// 수집한 주문 상품들의 우선순위 수집.
		List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderIds(orderIds);

		// 주문 상품들의 우선순위 점수 합산하여, 주문 하나당 우선순위 점수 수집.
		Map<Long, Integer> map = new HashMap<>();
		for (OrderItem orderItem : orderItems) {
			Long orderId = orderItem.getOrderId();
			map.put(orderId, orderItem.getPriority() + map.getOrDefault(orderId, 0));
		}

		// 주문 fk를 통해, 배송에 배치된 주문의 우선순위 점수에 따라, 배송순서 재배열 후, 배송 상태를 '배송 준비중'으로 전환
		toOngoingDeliveries.sort(Comparator.comparingInt(o -> map.get(o.getOrderId())));

		return null;
	}

	public List<Delivery> doDeliveriesPreparation() {
		// 유효한 배송 기사가 있는가?
		List<DeliveryDriver> drivers = deliveryDriverRepository.findValidDrivers()
			.stream().filter(d -> d.getStatus().equals(Status.ACTIVATED))
			.collect(Collectors.toList());

		List<Delivery> toPrepareDeliveries = deliveryRepository.findPreparedDeliveries(statusProductPreparation());

		List<Long> deliveryIds = toPrepareDeliveries
			.stream()
			.map(Delivery::getId)
			.collect(Collectors.toList());

		if (!drivers.isEmpty()) {
			// 배차가 가능한 배송차량이 있으면, 전부 '배송 준비'
			deliveryRepository.bulkUpdateDeliveryStatus(deliveryIds, statusDeliveryPreparation());
		} else {
			// 배차가 가능한 배송차량이 없으면, 상품 준비가 가능한, 배송은 전부 보류처리
			deliveryRepository.bulkUpdateDeliveryStatus(deliveryIds, statusDeliveryHold());
		}
		return toPrepareDeliveries;
	}

	public List<Delivery> doProductsPreparation() {
		List<Delivery> result = new ArrayList<>();

		// 상품 준비 단계
		List<Order> orders = orderRepository.findAll();

		List<Long> orderIds = orders
			.stream()
			.filter(Order::isCanceled)
			.map(Order::getId)
			.collect(Collectors.toList());

		List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderIds(orderIds);

		// 주문 상품의 상품 검색
		List<Long> itemIds = orderItems
			.stream()
			.map(OrderItem::getItemId)
			.collect(Collectors.toList());

		List<Item> items = itemRepository.findItemsByOrderIds(itemIds);

		// 검색한 상품에서 유효하지 않은 상품 pk 수집
		Set<Long> invalidItemIds = items
			.stream()
			.filter(i -> !i.getStatus().equals(Status.ACTIVATED))
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
			DeliveryStatus deliveryStatus = invalidOrderId.contains(id) ? this.statusDeliveryHold() :  statusProductPreparation();
			result.add(
				Delivery.builder()
					.orderId(id)
					.createTime(now)
					.deliveryStatus(deliveryStatus)
					.status(Status.ACTIVATED)
					.build()
			);
		});
		deliveryRepository.bulkInsert(result);
		return result;
	}

	@Override
	public DeliveryStatus statusProductPreparation() {
		return DeliveryStatus.PRODUCT_PREPARATION;
	}

	@Override
	public DeliveryStatus statusDeliveryPreparation() {
		return DeliveryStatus.DELIVERY_PREPARATION;
	}

	@Override
	public DeliveryStatus statusDeliveryHold() {
		return DeliveryStatus.DELIVERY_ON_HOLD;
	}

	@Override
	public DeliveryStatus statusDeliveryOngoing() {
		return DeliveryStatus.DELIVERY_ONGOING;
	}

	@Override
	public DeliveryStatus statusDeliveryComplete() {
		return DeliveryStatus.DELIVERY_COMPLETE;
	}
}
