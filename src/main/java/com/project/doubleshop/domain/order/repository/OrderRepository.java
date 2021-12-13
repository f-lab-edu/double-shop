package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.web.config.support.Pageable;

import java.util.List;

public interface OrderRepository {

    public void save(Order order);

    public Order findById(Long id);

    public List<Order> findAll(String userId, Pageable pageable);

    public Long count(String userId);

}
