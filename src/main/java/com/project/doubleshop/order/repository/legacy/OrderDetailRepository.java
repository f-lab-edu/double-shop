package com.project.doubleshop.order.repository.legacy;

import java.time.LocalDateTime;
import java.util.List;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.order.entity.OrderDetail;
import com.project.doubleshop.web.order.dto.OrderDetailResult;

public interface OrderDetailRepository {
	boolean save(OrderDetail orderDetail);

	List<OrderDetail> findById(Long orderId);

	List<OrderDetailResult> findWithItemByOrderId(Long orderId);

	Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds);

	Integer deleteOrderDetails(Status status);

	Integer batchInsert(List<OrderDetail> orderDetails);
}
