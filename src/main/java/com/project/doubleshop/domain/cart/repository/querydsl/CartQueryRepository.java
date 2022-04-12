package com.project.doubleshop.domain.cart.repository.querydsl;

import java.util.List;

import com.project.doubleshop.domain.cart.repository.querydsl.CartQueryResult;

public interface CartQueryRepository {
	List<CartQueryResult> findCartsJoinWithItem(Long memberId);
}
