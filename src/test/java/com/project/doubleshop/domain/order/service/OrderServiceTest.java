package com.project.doubleshop.domain.order.service;

import static com.project.doubleshop.domain.item.MockItem.*;
import static com.project.doubleshop.domain.member.service.MockMember.*;
import static com.project.doubleshop.domain.order.MockOrder.*;
import static com.project.doubleshop.domain.order.MockOrderDetail.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.CartService;
import com.project.doubleshop.domain.cart.service.MockCart;
import com.project.doubleshop.domain.item.MockItem;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.member.service.MockMember;
import com.project.doubleshop.domain.order.MockOrder;
import com.project.doubleshop.domain.order.MockOrderDetail;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderDetail;
import com.project.doubleshop.domain.order.repository.OrderDetailRepository;
import com.project.doubleshop.domain.order.repository.OrderRepository;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.config.support.SimplePageRequest;
import com.project.doubleshop.web.order.dto.OrderDetailDto;
import com.project.doubleshop.web.order.dto.OrderDetailResult;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

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
		List<Long> itemIds = List.of(Item1.ID);
		Order order = Order1.ORDER;
		List<Item> items = List.of(Item1.ITEM);
		List<Cart> carts = List.of(MockCart.Cart1.CART);

		given(cartService.findCartsByMemberId(Member1.ID)).willReturn(carts);
		given(itemService.findItemsInItemIds(itemIds)).willReturn(items);
		given(orderRepository.save(order)).willReturn(true);
		given(orderDetailRepository.batchInsert(anyList())).willReturn(1);

		Order result = orderService.createOrder(Member1.ID, order);

		assertThat(result.getId()).isEqualTo(order.getId());
	}

	@Test
	void cancelOrder() {
		OrderDetailResult orderDetailResult = new OrderDetailResult(Order1.ID, Item1.ID, Item1.NAME, Item1.PRICE,
			Item1.STOCK, OrderDetail1.QUANTITY);

		given(orderRepository.findByIdAndMemberId(Order1.ID, Order1.MEMBER_ID)).willReturn(Order1.ORDER);
		given(orderDetailRepository.findWithItemByOrderId(Order1.ID)).willReturn(List.of(orderDetailResult));
		given(itemService.findItemsInItemIds(List.of(Item1.ID))).willReturn(List.of(Item1.ITEM));
		given(orderRepository.updateOrderStatus(any(OrderStatusRequest.class))).willReturn(1);

		Order order = orderService.cancelOrder(Order1.ID, Order1.MEMBER_ID);

		assertThat(order.getId()).isEqualTo(Order1.ID);
	}

	@Test
	void findOrderByMemberId() {
		Pageable pageable = new SimplePageRequest();

		given(orderRepository.findByMemberId(Member1.ID, pageable)).willReturn(List.of(Order1.ORDER));

		List<Order> orders = orderService.findOrderByMemberId(Member1.ID, pageable);

		assertThat(orders.size()).isEqualTo(1);
	}

	@Test
	void findOrderDetail() {
		given(orderDetailRepository.findWithItemByOrderId(Order1.ID)).willReturn(anyList());

		OrderDetailDto orderDetail = orderService.findOrderDetail(Order1.MEMBER_ID, Order1.ID);

		assertThat(orderDetail).isNotNull();
	}
}