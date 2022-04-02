package com.project.doubleshop.domain.order.service;

import static com.project.doubleshop.domain.member.service.MockMember.*;
import static com.project.doubleshop.domain.order.MockOrder.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.CartService;
import com.project.doubleshop.domain.cart.service.MockCart;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.order.MockOrderDetail;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	OrderDetailRepository orderDetailRepository;

	@Mock
	OrderRepository orderRepository;

	@Mock
	CartService cartService;

	@Mock
	ItemService itemService;

	@InjectMocks
	OrderService orderService;

	@Test
	void createOrder() {
		Long memberId = Member1.MEMBER.getId();
		List<Cart> carts = List.of(MockCart.Cart1.CART);
		Order order = Order1.ORDER;
		given(cartService.findCartsByMemberId(memberId)).willReturn(carts);
		given(orderRepository.save(order)).willReturn(Order1.ORDER);

		Order result = orderService.createOrder(memberId, order);

		assertThat(result).isNotNull();
	}

	@Test
	void cancelOrder() {
		Long memberId = Member1.MEMBER.getId();
		Long orderId = Order1.ID;
		given(orderRepository.findByIdAndMemberId(orderId, memberId)).willReturn(Optional.of(Order1.ORDER));
		given(orderDetailRepository.findByOrderId(orderId)).willReturn(List.of(MockOrderDetail.OrderDetail1.ORDER_DETAIL));

		Order order = orderService.cancelOrder(memberId, orderId);

		assertThat(order).isNotNull();
	}
}