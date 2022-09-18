package com.project.doubleshop.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.order.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findByOrderId(Long orderId);
	@Query(value = "select od from OrderDetail od"
		+ " join Order o on o.id = od.order.id"
		+ " join Item i on i.id = od.item.id"
		+ " where o.id = :orderId"
		+ " and o.member.id = :memberId"
		+ " and o.status = :status"
		+ " and i.status = :status"
		)
	List<OrderDetail> findOrderDetailByMemberIdAndStatus(Long orderId, Long memberId, Status status);
	void deleteAllByOrderIn(List<Long> orderIds);
}
