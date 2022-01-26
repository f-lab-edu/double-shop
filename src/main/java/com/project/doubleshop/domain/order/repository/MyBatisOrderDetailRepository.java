package com.project.doubleshop.domain.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.OrderDetail;
import com.project.doubleshop.domain.order.mapper.OrderDetailMapper;
import com.project.doubleshop.web.order.dto.OrderDetailResult;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderDetailRepository implements OrderDetailRepository {
	private final OrderDetailMapper mapper;

	@Override
	public boolean save(OrderDetail orderDetail) {
		return mapper.insertOrderDetail(orderDetail) != 0;
	}

	@Override
	public List<OrderDetail> findById(Long orderId) {
		return mapper.selectOrderDetailByOrderId(orderId);
	}

	@Override
	public List<OrderDetailResult> findWithItemByOrderId(Long orderId) {
		return mapper.selectOrderDetailWithItemByOrderId(orderId);
	}

	@Override
	public Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds) {
		return mapper.updateOrderDetailStatus(statusCode, statusUpdateTime, orderIds);
	}

	@Override
	public Integer deleteOrderDetails(Status status) {
		return mapper.deleteOrderDetails(status.getValue());
	}

	@Override
	public Integer batchInsert(List<OrderDetail> orderDetails) {
		return mapper.batchInsertOrderDetails(orderDetails);
	}
}
