package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepository implements OrderRepository {

    private final OrderMapper mapper;

    @Override
    public void save(Order order) {
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAllByString(OrderSearch orderSearch) {
        // 주문 상태 검색

        // 회원 이름 검색

        return null;
    }

    @Override
    public List<Order> findAllWithMemberDelivery() {
        return null;
    }

    @Override
    public List<Order> findAllWithItem() {
        return null;
    }

}
