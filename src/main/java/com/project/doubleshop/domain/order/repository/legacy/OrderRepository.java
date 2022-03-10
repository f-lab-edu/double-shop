package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {
    boolean save(Order order);

    Order findById(Long id);

    Order findByIdAndMemberId(Long id, Long memberId);

    List<Order> findByMemberId(Long memberId, Pageable pageable);

    Integer updateOrderStatus(OrderStatusRequest orderStatusRequest);

    Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds);

    Integer deleteOrders(Status status);
}
