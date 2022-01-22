package com.project.doubleshop.domain.order.mapper;

import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.web.order.dto.OrderStatusRequest;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    Order selectByOrderId(Long id);
    Order selectByIdAndMemberId(Long id, Long memberId);
    List<Order> selectByMemberId(Long memberId, Integer size, Long page);
    int updateOrderStatus(OrderStatusRequest orderStatusRequest);
    int updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds);
    int deleteOrders(Integer statusCode);
}
