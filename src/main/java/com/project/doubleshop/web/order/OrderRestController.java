package com.project.doubleshop.web.order;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.order.entity.Order;
import com.project.doubleshop.order.entity.OrderDetail;
import com.project.doubleshop.order.service.OrderService;
import com.project.doubleshop.web.item.dto.ItemApiResult;
import com.project.doubleshop.web.order.dto.OrderDetailDto;
import com.project.doubleshop.web.order.dto.OrderApiResult;
import com.project.doubleshop.web.order.dto.OrderForm;

import lombok.RequiredArgsConstructor;


@RequestMapping("api")
@RequiredArgsConstructor
public class OrderRestController {

	private final OrderService orderService;

	@PostMapping("member/{memberId}/order")
	public ResponseEntity<OrderApiResult> createNewOrder(@PathVariable Long memberId, @RequestBody OrderForm orderForm) {
		Order order = orderService.createOrder(memberId, Order.convertToOrder(orderForm));
		return ResponseEntity.ok(new OrderApiResult(order));
	}

	@GetMapping(value = "member/{memberId}/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderApiResult>> findMyOrders(@PathVariable Long memberId, Pageable pageable) {
		List<Order> orders = orderService.findOrderByMemberId(memberId, pageable);
		return ResponseEntity.ok(
			orders.stream()
				.map(OrderApiResult::new)
				.collect(toList()));
	}

	@GetMapping(value = "member/{memberId}/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDetailDto> findMyOrderDetail(@PathVariable Long memberId, @PathVariable Long orderId) {
		List<OrderDetail> orderDetails = orderService.searchMyOrder(memberId, orderId);

		OrderApiResult orderApiResult = new OrderApiResult(orderDetails.get(0).getOrder());

		List<ItemApiResult> itemApiResults = orderDetails.stream()
			.map(orderDetail -> new ItemApiResult(orderDetail.getItem()))
			.collect(toList());

		return ResponseEntity.ok(new OrderDetailDto(orderApiResult, itemApiResults));
	}

	@PatchMapping("member/{memberId}/order/{orderId}")
	public ResponseEntity<OrderApiResult> cancelOrder(@PathVariable Long memberId, @PathVariable Long orderId) {
		return ResponseEntity.ok(new OrderApiResult(orderService.cancelOrder(memberId, orderId)));
	}

	@PatchMapping("order/{id}/status")
	public ResponseEntity<Boolean> requestUpdateItemStatus(@RequestParam Status status, @PathVariable Long id) {
		return ResponseEntity.ok(orderService.updateStatus(id, status));
	}

	@DeleteMapping("order")
	public ResponseEntity<Integer> deleteAssignedItems(@RequestParam Status status) {
		return ResponseEntity.ok(orderService.removeStatusDel(status));
	}
}
