package com.project.doubleshop.domain.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.mapper.OrderMapper;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepository implements OrderRepository {

	private final OrderMapper orderMapper;

	@Override
	public boolean save(Order order) {
		return orderMapper.insertOrder(order) != 0;
	}

	@Override
	public Order findById(Long id) {
		return orderMapper.selectByOrderId(id);
	}

	@Override
	public List<Order> findByMemberId(Long memberId, Pageable pageable) {
		return orderMapper.selectByMemberId(memberId, pageable.size(), pageable.page());
	}

	@Override
	public Integer updateOrderStatus(OrderStatusRequest orderStatusRequest) {
		return orderMapper.updateOrderStatus(orderStatusRequest);
	}

	@Override
	public Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds) {
		return orderMapper.updateStatus(statusCode, statusUpdateTime, orderIds);
	}

	@Override
	public Integer deleteOrders(Status status) {
		return orderMapper.deleteOrders(status.getValue());
	}
}
