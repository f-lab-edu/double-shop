package com.project.doubleshop.web.order;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.service.OrderService;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.order.dto.OrderDetailDto;
import com.project.doubleshop.web.order.dto.OrderDetailResult;
import com.project.doubleshop.web.order.dto.OrderDto;
import com.project.doubleshop.web.order.dto.OrderForm;
import com.project.doubleshop.web.order.dto.OrderItemDto;

import lombok.RequiredArgsConstructor;


@RequestMapping("api")
@RequiredArgsConstructor
public class OrderRestController {

	private final OrderService orderService;

	@PostMapping("member/{memberId}/order")
	public ResponseEntity<OrderDto> createNewOrder(@PathVariable Long memberId, @RequestBody OrderForm orderForm) {
		Order order = orderService.createOrder(memberId, Order.convertToOrder(orderForm));
		return ResponseEntity.ok(new OrderDto(order));
	}

	@GetMapping("member/{memberId}/order")
	public ResponseEntity<List<OrderDto>> findMyOrders(@PathVariable Long memberId, Pageable pageable) {
		List<Order> orders = orderService.findOrderByMemberId(memberId, pageable);
		return ResponseEntity.ok(
			orders.stream()
				.map(OrderDto::new)
				.collect(toList()));
	}

	@GetMapping("member/{memberId}/order/{orderId}")
	public ResponseEntity<OrderDetailDto> findMyOrderDetail(@PathVariable Long memberId, @PathVariable Long orderId) {
		return ResponseEntity.ok(orderService.findOrderDetail(memberId, orderId));
	}

	@PatchMapping("member/{memberId}/order/{orderId}")
	public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long memberId, @PathVariable Long orderId) {
		return ResponseEntity.ok(new OrderDto(orderService.cancelOrder(memberId, orderId)));
	}
}
