package com.project.doubleshop.domain.cart.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCartRepository implements CartRepository {

	private final CartMapper mapper;

	@Override
	public List<Cart> findCartsByMemberId(Long memberId) {
		return mapper.selectCartByMemberId(memberId);
	}

	@Override
	public boolean saveCart(Cart cart) {
		return mapper.insertCart(cart) != 0;
	}

	@Override
	public int deleteCarts(Long memberId, List<Long> itemIds) {
		return mapper.deleteCart(memberId, itemIds);
	}
}
