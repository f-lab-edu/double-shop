package com.project.doubleshop.domain.order.mapper;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    Order selectByOrderId(Long id);
    List<Order> selectAllOrders(Pageable pageable);
    int updateOrder(Order order);
    int updateOrderStatus(StatusRequest statusRequest);
    int deleteOrder(Status status);
}
