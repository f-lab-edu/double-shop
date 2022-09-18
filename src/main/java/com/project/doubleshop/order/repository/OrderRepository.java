package com.project.doubleshop.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query(value = "select o from Order o where o.id = :orderId and  o.member.id = :memberId")
	Optional<Order> findByIdAndMemberId(Long orderId, Long memberId);
	Page<Order> findOrdersByMemberIdAndStatus(Long memberId, Status status, Pageable pageable);

	@Query(value = "select o.id from Order o where o.status = :status")
	List<Long> findIdsByStatus(Status status);
}
