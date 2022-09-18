package com.project.doubleshop.order.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.order.entity.OrderDetail;
import com.project.doubleshop.web.order.dto.OrderDetailResult;

@Mapper
public interface OrderDetailMapper {
	int insertOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> selectOrderDetailByOrderId(Long orderId);
	List<OrderDetailResult> selectOrderDetailWithItemByOrderId(Long orderId);
	int updateOrderDetailStatus(int statusCode, LocalDateTime statusUpdateTime, List<Long> orderIds);
	int deleteOrderDetails(Integer statusCode);
	int batchInsertOrderDetails(List<OrderDetail> orderDetails);
}
