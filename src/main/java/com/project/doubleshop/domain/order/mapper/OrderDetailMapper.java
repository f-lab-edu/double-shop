package com.project.doubleshop.domain.order.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.order.entity.OrderDetail;
import com.project.doubleshop.web.order.dto.OrderDetailResult;

@Mapper
public interface OrderDetailMapper {
	int insertOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> selectByOrderId(Long orderId);
	List<OrderDetailResult> selectWithItemByOrderId(Long orderId);
	int updateStatus(int statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds);
	int deleteOrderDetails(Integer statusCode);
	int batchInsertOrderDetails(List<OrderDetail> orderDetails);
}
