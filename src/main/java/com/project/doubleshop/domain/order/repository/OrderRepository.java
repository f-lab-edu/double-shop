package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.common.Manageable;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

import java.util.List;

public interface OrderRepository extends Manageable<StatusRequest> {

    boolean save(Order order);

    Order findById(Long id);

    List<Order> findAll(Pageable pageable);

}
