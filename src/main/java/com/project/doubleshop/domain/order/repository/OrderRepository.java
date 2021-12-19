package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.order.entity.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository {

    boolean save(Order order);

    Order findById(Long id);

    List<Order> findAll(String userId, Pageable pageable);

    Long count(String userId);

}
