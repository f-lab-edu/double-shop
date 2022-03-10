package com.project.doubleshop.domain.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "select o from Order o where o.id = :orderId and  o.member.id = :memberId")
	Optional<Order> findByIdAndMemberId(Long orderId, Long memberId);
}
