package com.project.doubleshop.domain.cart.repository.querydsl;

import java.util.List;

public interface CartQueryRepository {
	List<CartQueryResult> findCartsJoinWithItem(Long memberId);
}
