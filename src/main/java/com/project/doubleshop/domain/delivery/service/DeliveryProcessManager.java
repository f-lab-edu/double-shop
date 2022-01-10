package com.project.doubleshop.domain.delivery.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
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

		// List<DeliveryDriver> drivers = deliveryDriverRepository.findValidDrivers()
		// 	.stream().filter(d -> d.getStatus().equals(Status.ACTIVATED))
		// 	.collect(Collectors.toList());
		//
		// List<Delivery> toBeginDeliveries = deliveryRepository.findDeliveriesByDeliveryStatus(statusProductPreparation());
		//
		// List<Long> orderIds = new ArrayList<>();
		//
		// // 배송 상태를 업데이트할 배송의 pk와 주문 fk 수집.
		// toBeginDeliveries.forEach(d -> {
		// 	orderIds.add(d.getOrderId());
		// });
		//
		// // 수집한 주문 상품들의 우선순위 수집.
		// List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderIds(orderIds);
		//
		// // 주문 상품들의 우선순위 점수 합산하여, 주문 하나당 우선순위 점수 수집.
		// Map<Long, Integer> map = new HashMap<>();
		// for (OrderItem orderItem : orderItems) {
		// 	Long orderId = orderItem.getOrderId();
		// 	map.put(orderId, orderItem.getPriority() + map.getOrDefault(orderId, 0));
		// }
		//
		// toBeginDeliveries.sort(Comparator.comparingInt(o -> map.get(o.getOrderId())));

		return null;
	}

	public List<Delivery> doDeliveriesPreparation() {

		// 상품 준비 단계에 있는 배송들의 주문 fk 조회
		List<Delivery> toPrepareDeliveries = deliveryRepository.findDeliveriesByDeliveryStatus(statusProductPreparation());
		List<Long> orderIds = toPrepareDeliveries.stream()
			.map(Delivery::getOrderId)
			.collect(Collectors.toList());

		// 주문 fk에 속하는 주문 조회
		List<Order> orders = orderRepository.findByIds(orderIds);

		// 그 주문에서, 취소된 것과 그렇지 않은 것을 분류
		List<Long> isCanceled = new ArrayList<>();
		List<Long> isNotCanceled = new ArrayList<>();

		for (Order order : orders) {
			if (order.isCanceled()) {
				isCanceled.add(order.getId());
			} else {
				isNotCanceled.add(order.getId());
			}
		}

		// 분류하고, 해당 배송들의 배송상태 업데이트
		deliveryRepository.bulkUpdateDeliveryStatusByOrderIds(isCanceled, statusDeliveryHold());
		deliveryRepository.bulkUpdateDeliveryStatusByOrderIds(isNotCanceled, statusDeliveryPreparation());
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
