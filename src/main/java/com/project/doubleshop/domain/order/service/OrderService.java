package com.project.doubleshop.domain.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.CartService;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderDetail;
import com.project.doubleshop.domain.order.entity.constant.OrderConstant;
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	private final CartService cartService;
	private final ItemService itemService;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

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

	public Order findByIdAndMemberId(Long memberId, Long orderId) {
		return orderRepository.findByIdAndMemberId(memberId, orderId).orElseThrow(() ->
			new NotFoundException(String.format("Order id %d not found", orderId)));
	}
}
