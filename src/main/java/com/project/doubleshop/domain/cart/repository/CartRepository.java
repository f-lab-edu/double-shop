package com.project.doubleshop.domain.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.common.Status;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query(value = "select c.id from cart c where c.status = ?", nativeQuery = true)
	List<Long> findIdsByStatus(Status status);
	Cart findCartByIdAndMemberId(Long cartId, Long memberId);
	List<Cart> findCartsByMemberId(Long memberId);
}
