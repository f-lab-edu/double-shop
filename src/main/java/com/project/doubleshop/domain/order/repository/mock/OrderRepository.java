package com.project.doubleshop.domain.order.repository.mock;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.project.doubleshop.domain.order.entity.mock.Order;

// TODO 주문 도메인 데이터 액세스 & 서비스 로직 구현.
public interface OrderRepository {

    boolean save(Order order);

    Order findById(Long id);

    List<Order> findAll();

    // TODO order pk를 모아서 in(...)로 주문 목록을 조회하는 쿼리문 구현.
	List<Order> findByIds(List<Long> orderIds);
}
