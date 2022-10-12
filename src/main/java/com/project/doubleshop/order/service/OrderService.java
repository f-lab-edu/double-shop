package com.project.doubleshop.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.cart.entity.Cart;
import com.project.doubleshop.cart.service.CartService;
import com.project.doubleshop.common.Status;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.item.domain.Item;
import com.project.doubleshop.item.legacy.service.ItemService;
import com.project.doubleshop.order.entity.Order;
import com.project.doubleshop.order.entity.OrderDetail;
import com.project.doubleshop.order.entity.constant.OrderConstant;
import com.project.doubleshop.order.repository.OrderDetailRepository;
import com.project.doubleshop.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	private final CartService cartService;
	private final ItemService itemService;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

	// 주문 하기
	@Transactional
	public Order createOrder(Long memberId, Order order) {
		List<OrderDetail> orderDetails = new ArrayList<>();
		List<Cart> carts = cartService.findCartsByMemberId(memberId);
		Order newOrder = orderRepository.save(order);
		for (Cart cart : carts) {
			Item item = cart.getItem();
			Integer cartQuantity = cart.getQuantity();
			item.decreaseStock(cartQuantity);
			OrderDetail orderDetail = OrderDetail.builder()
				.order(newOrder)
				.item(item)
				.quantity(cartQuantity)
				.price(item.getPrice())
				.build();
			orderDetails.add(orderDetail);
		}
		orderDetailRepository.saveAll(orderDetails);
		return newOrder;
	}

	// 주문 취소하기
	@Transactional
	public Order cancelOrder(Long memberId, Long orderId) {
		Order order = findByIdAndMemberId(memberId, orderId);
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
		for (OrderDetail orderDetail : orderDetails) {
			Item item = orderDetail.getItem();
			Integer orderDetailQuantity = orderDetail.getQuantity();
			item.increaseStock(orderDetailQuantity);
		}
		order.setOrderStatus(OrderConstant.CANCELED);
		return order;
	}

	public List<Order> findOrderByMemberId(Long memberId, Pageable pageable) {
		return orderRepository.findOrdersByMemberIdAndStatus(memberId, Status.ACTIVATED, pageable).getContent();
	}

	// 내 주문
	public List<OrderDetail> searchMyOrder(Long memberId, Long orderId) {
		return orderDetailRepository.findOrderDetailByMemberIdAndStatus(orderId, memberId, Status.ACTIVATED);
	}

	public Order findByIdAndMemberId(Long memberId, Long orderId) {
		return orderRepository.findByIdAndMemberId(memberId, orderId).orElseThrow(() ->
			new NotFoundException(String.format("Order id %d not found", orderId)));
	}

	@Transactional
	public Boolean updateStatus(Long id, Status status) {
		Order order = findById(id);
		Status previous = order.getStatus();
		order.saveStatus(status);
		return !previous.equals(order.getStatus());
	}

	@Transactional
	public Integer removeStatusDel(Status status) {
		List<Long> orderIds = orderRepository.findIdsByStatus(status);
		orderRepository.deleteAllById(orderIds);
		orderDetailRepository.deleteAllByOrderIn(orderIds);
		return orderIds.size();
	}

	public Order findById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() ->
			new NotFoundException(String.format("Order id %d not found", orderId)));
	}
}
