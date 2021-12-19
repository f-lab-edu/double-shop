package com.project.doubleshop.domain.order.mapper;

import com.project.doubleshop.domain.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    Order selectByOrderId(Long id);
    List<Order> selectAll();
    int updateOrder(Order order);
}
