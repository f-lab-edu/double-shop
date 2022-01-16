package com.project.doubleshop.domain.cart.repository;

import java.util.List;

import com.project.doubleshop.domain.cart.entity.Cart;

public interface CartRepository {
	List<Cart> findCartsByMemberId(Long memberId);
	boolean saveCart(Cart cart);
	int deleteCarts(Long memberId, List<Long> cartIds);
}
