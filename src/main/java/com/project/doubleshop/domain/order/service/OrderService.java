package com.project.doubleshop.domain.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.domain.utils.ExceptionUtils;
import com.project.doubleshop.web.item.dto.ItemStockQuery;

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

		updateItemStockBeforeOrder(quantityPerItem, pricePerItem, itemIds);

		Order newOrder = saveAndGetOrder(order);

		List<OrderDetail> orderDetails = collectOrderDetails(itemIds, quantityPerItem, pricePerItem, newOrder);

		insertBatch(orderDetails);

		return newOrder;
	}

	@Transactional
	public void updateItemStockBeforeOrder(Map<Long, Integer> quantityPerItem, Map<Long, Integer> pricePerItem,
		List<Long> itemIds) {

		List<Long> invalidItemIds = new ArrayList<>();
		List<ItemStockQuery> queryList = new ArrayList<>();
		List<Item> items = itemService.findItemsInItemIds(itemIds);

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

	public boolean insertBatch(List<OrderDetail> orderDetails) {
		Integer resultCnt = orderDetailRepository.batchInsert(orderDetails);
		if (resultCnt == null || resultCnt != orderDetails.size()) {
			if (resultCnt == null) {
				throw new NullPointerException("'NullPointerException' occurred while batch insert orderDetails");
			} else {
				return false;
			}
		}
		return true;
	}
}
