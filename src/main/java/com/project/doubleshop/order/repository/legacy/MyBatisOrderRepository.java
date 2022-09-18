package com.project.doubleshop.order.repository.legacy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.order.entity.Order;
import com.project.doubleshop.order.mapper.OrderMapper;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepository implements OrderRepository {

	private final OrderMapper mapper;

	@Override
	public boolean save(Order order) {
		return mapper.insertOrder(order) != 0;
	}

	@Override
	public Order findById(Long id) {
		return mapper.selectOrderByOrderId(id);
	}

	@Override
	public Order findByIdAndMemberId(Long id, Long memberId) {
		return mapper.selectOrderByIdAndMemberId(id, memberId);
	}

	@Override
	public List<Order> findByMemberId(Long memberId, Pageable pageable) {
		return mapper.selectByMemberId(memberId, pageable.size(), pageable.page());
	}

	@Override
	public Integer updateOrderStatus(OrderStatusRequest orderStatusRequest) {
		return mapper.updateOrderStatus(orderStatusRequest);
	}

	@Override
	public Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds) {
		return mapper.updateStatus(statusCode, statusUpdateTime, orderIds);
	}

	@Override
	public Integer deleteOrders(Status status) {
		return mapper.deleteOrders(status.getValue());
	}
}
