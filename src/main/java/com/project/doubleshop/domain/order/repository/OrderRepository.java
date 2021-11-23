package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.order.entity.Order;

import java.util.List;

public interface OrderRepository {

    /* Order를 저장하는 메서드 */
    public void save(Order order);

    /* 단건 조회 메서드 */
    public Order findById(Long id);

    public List<Order> findAllByString(OrderSearch orderSearch);

    public List<Order> findAllWithMemberDelivery();

    public List<Order> findAllWithItem();

}
