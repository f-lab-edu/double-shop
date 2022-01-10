package com.project.doubleshop.domain.delivery.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

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
			// case "3":
			// 	// 배송 시작 -> 배송 중
			// 	break;
			// case "4":
			// 	// 배송 중 -> 배송 완료 || 배송 보류
			// 	break;
		}
	}

	private List<Delivery> doDeliveryBegin() {

		// 배송기사를 조회한다.
		List<DeliveryDriver> drivers = deliveryDriverRepository.findValidDrivers();

		// '배송 준비' 가 완료된 배송목록 조회
		List<Delivery> toBeginDeliveries = deliveryRepository.findDeliveriesByDeliveryStatus(statusDeliveryPreparation());
		Map<Long, Long> deliveryAndOrder = new HashMap<>();
		List<Long> orderIds = new ArrayList<>();
		for (Delivery delivery : toBeginDeliveries) {
			orderIds.add(delivery.getId());
			deliveryAndOrder.put(delivery.getId(), delivery.getOrderId());
		}

		// 배송 목록에서 주문 id 수집하여 해당 주문 조회
		List<Order> orders = orderRepository.findByIds(orderIds);

		// 주문 id에 속한 주문상품 조회
		List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderIds(orderIds);


		// 주문 별 개별 아이템의 무게들의 총 합 수집
		Map<Long, Integer> weightPerOrder = orderItems
			.stream()
			.collect(Collectors.toMap(OrderItem::getOrderId, OrderItem::getItemWeight, Integer::sum));

		// 주문 별, 주문 타입 수치 수집
		Map<Long, Integer> priorityPerOrder = orders
			.stream()
			.collect(
				Collectors.toMap(Order::getId, order -> order.getOrderType().getValue())
		);

		// 배송의 주문 타입에 따라, 배송 순서 재배열
		List<DeliveryInfo> deliveries = new ArrayList<>();
		for (Long deliveryId : deliveryAndOrder.keySet()) {
			Long orderId = deliveryAndOrder.get(deliveryId);
			Integer priority = priorityPerOrder.get(orderId);
			Integer weight = weightPerOrder.get(orderId);
			deliveries.add(new DeliveryInfo(deliveryId, priority, weight));
		}

		// 정렬 된 배송을 통해, 배송 운전기사와 배차 작업 수행.
		List<DispatchDriver> result = dispatchDriverToDelivery(drivers, deliveries);

		if (result != null) {
			deliveryRepository.batchUpdateDeliveryDriver(result);
			if (result.size() < deliveries.size()) {
				Set<Long> ids = result.stream().map(DispatchDriver::getDeliveryId).collect(Collectors.toSet());
				List<Long> invalidIds = deliveries.stream()
					.map(DeliveryInfo::getDeliveryId)
					.filter(deliveryId -> !ids.contains(deliveryId))
					.collect(Collectors.toList());

				deliveryRepository.batchUpdateDeliveryStatusByDeliveryId(invalidIds, statusDeliveryHold());
			}
		}

		return toBeginDeliveries;
	}

	private List<DispatchDriver> dispatchDriverToDelivery (List<DeliveryDriver> drivers, List<DeliveryInfo> deliveries) {
		if (drivers.isEmpty() || deliveries.isEmpty()) {
			return null;
		}

		deliveries.sort((o1, o2) -> {
			if (o1.getPriority().equals(o2.getPriority())) {
				return o2.getWeight() - o1.getWeight();
			} else {
				return o1.getPriority() - o2.getPriority();
			}
		});

		Queue<DeliveryInfo> deliveryQueue = new LinkedList<>();
		deliveryQueue.addAll(deliveries);

		DeliveryDriver[] deliveryDrivers = drivers.toArray(new DeliveryDriver[0]);
		Arrays.sort(deliveryDrivers, (o1, o2) -> o2.getCapacity() - o1.getCapacity());
		List<DispatchDriver> result = new ArrayList<>();

		int idx = 0;
		DeliveryInfo deliveryInfo = deliveryQueue.remove();
		int weight = deliveryInfo.getWeight();
		Integer max = deliveryDrivers[0].getCapacity();
		while (idx < deliveryDrivers.length) {
			DeliveryDriver deliveryDriver = deliveryDrivers[idx];
			Integer capacity = deliveryDriver.getCapacity();
			if (capacity < weight) {
				if (weight <= max) {
					deliveryQueue.add(deliveryInfo);
				}
				deliveryInfo = deliveryQueue.remove();
				weight = deliveryInfo.getWeight();
			}
			while (capacity >= weight) {
				deliveryDriver.decreaseCapacity(weight);
				capacity = deliveryDriver.getCapacity();
				result.add(new DispatchDriver(deliveryInfo.getDeliveryId(), deliveryDriver.getId()));
				if (deliveryQueue.isEmpty()) {
					return result;
				}
				deliveryInfo = deliveryQueue.remove();
				weight = deliveryInfo.getWeight();
			}
			idx++;
		}
		return result;

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

		List<Item> items = itemRepository.findItemsByIds(itemIds);

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
