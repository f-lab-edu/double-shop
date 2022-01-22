package com.project.doubleshop.domain.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.address.repository.AddressRepository;
import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.CartService;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderDetail;
import com.project.doubleshop.domain.order.entity.constant.OrderConstant;
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.domain.utils.ExceptionUtils;
import com.project.doubleshop.web.item.dto.ItemStockQuery;
import com.project.doubleshop.web.order.dto.OrderDetailResult;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	private final CartService cartService;
	private final ItemService itemService;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final AddressRepository addressRepository;

	@Transactional
	public Order createOrder(Long memberId, Order order) {

		List<Cart> carts = cartService.findCartsByMemberId(memberId);

		List<Long> itemIds = new ArrayList<>();
		Map<Long, Integer> quantityPerItem = new HashMap<>();
		Map<Long, Integer> pricePerItem = new HashMap<>();

		// 장바구니 조회 후, 장바구니에 담긴 상품 id와 상품 별 구매희망 갯수(quantity)를 수집한다.
		for (Cart cart : carts) {
			Long itemId = cart.getItemId();
			itemIds.add(itemId);
			quantityPerItem.put(itemId, cart.getQuantity());
		}

		List<Item> items = itemService.findItemsInItemIds(itemIds);

		updateItemStockBeforeOrder(quantityPerItem, pricePerItem, items);

		Order newOrder = saveAndGetOrder(order);

		List<OrderDetail> orderDetails = collectOrderDetails(itemIds, quantityPerItem, pricePerItem, newOrder);

		insertBatch(orderDetails);

		return newOrder;
	}

	@Transactional
	public void cancelOrder(Long memberId, Long orderId) {
		Order order = findByOrderIdAndMemberId(orderId, memberId);

		List<OrderDetailResult> orderDetailWithItems = orderDetailRepository.findWithItemByOrderId(orderId);

		Map<Long, Integer> quantityPerItem = new HashMap<>();
		Map<Long, Integer> pricePerItem = new HashMap<>();
		List<Long> itemIds = new ArrayList<>();

		for (OrderDetailResult orderDetailResult : orderDetailWithItems) {
			Long itemId = orderDetailResult.getItemId();
			quantityPerItem.put(itemId, -orderDetailResult.getQuantity());
			pricePerItem.put(itemId, -orderDetailResult.getPrice());
			itemIds.add(itemId);
		}

		List<Item> items = itemService.findItemsInItemIds(itemIds);

		updateOrderStatus(memberId, orderId, OrderConstant.CANCELED);

		updateItemStockBeforeOrder(quantityPerItem, pricePerItem, items);
	}

	@Transactional
	public void updateOrderStatus(Long memberId, Long orderId, int canceled) {
		LocalDateTime now = LocalDateTime.now();
		orderRepository.updateOrderStatus(new OrderStatusRequest(orderId, canceled, now, memberId));
	}

	@Transactional
	public void updateItemStockBeforeOrder(Map<Long, Integer> quantityPerItem, Map<Long, Integer> pricePerItem,
		List<Item> items) {

		List<Long> invalidItemIds = new ArrayList<>();
		List<ItemStockQuery> queryList = new ArrayList<>();

		for (Item item : items) {
			Long itemId = item.getId();
			Integer itemStock = item.getStock();
			Integer cartItemQuantity = quantityPerItem.get(itemId);
			if (cartItemQuantity > itemStock) {
				invalidItemIds.add(itemId);
			} else {
				queryList.add(new ItemStockQuery(itemId, itemStock - cartItemQuantity));
				pricePerItem.put(itemId, item.getPrice());
			}
		}

		if (invalidItemIds.size() != 0) {
			// 재고가 없는 상품이 발생하여 예외처리
			ExceptionUtils.findInvalidIdsAndThrowException(invalidItemIds, "Out of stock item id");
		}
		itemService.updateItems(queryList);
	}

	public Order findByOrderIdAndMemberId(Long orderId, Long memberId) {
		return Optional.of(
			orderRepository.findByIdAndMemberId(orderId, memberId))
			.orElseThrow(() -> new NullPointerException(String.format("Cannot find order id %d", orderId)));
	}

	private List<OrderDetail> collectOrderDetails(List<Long> itemIds, Map<Long, Integer> quantityPerItem, Map<Long, Integer> pricePerItem,
		Order newOrder) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		Long orderId = newOrder.getId();
		LocalDateTime now = LocalDateTime.now();
		for (Long itemId : itemIds) {
			Integer quantity = quantityPerItem.get(itemId);
			Integer price = pricePerItem.get(itemId);
			orderDetails.add(new OrderDetail(orderId, itemId, quantity, price, Status.ACTIVATED, now));
		}
		return orderDetails;
	}

	@Transactional
	public Order saveAndGetOrder(Order order) {
		if (saveOrder(order)) {
			return order;
		} else {
			throw new NullPointerException("Insert and get order fail");
		}
	}

	@Transactional
	public boolean saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Transactional
	public void insertBatch(List<OrderDetail> orderDetails) {
		Integer resultCnt = orderDetailRepository.batchInsert(orderDetails);
		if (resultCnt == null || resultCnt != orderDetails.size()) {
			if (resultCnt == null) {
				throw new NullPointerException("'NullPointerException' occurred while batch insert orderDetails");
			} else {
				throw new IllegalArgumentException("Batch insert OrderDetail fail due to invalid orderDetails.");
			}
		}
	}
}
