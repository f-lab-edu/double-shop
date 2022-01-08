package com.project.doubleshop.domain.order.repository.mock;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.project.doubleshop.domain.order.entity.mock.Order;

public interface OrderRepository {

    boolean save(Order order);

    Order findById(Long id);

    List<Order> findAll();
}
