package com.project.doubleshop.domain.cart.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyBatisCartRepository implements CartRepository {

	private final CartMapper mapper;

	@Override
	public Cart findCartById(Long cartId, Long memberId) {
		return mapper.selectCartById(cartId, memberId);
	}

	@Override
	public List<Cart> findCartInIds(List<Long> cartIds, Long memberId) {
		return mapper.selectCartInIds(cartIds, memberId);
	}

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

	@Override
	public int updateCarts(Integer quantity, Long id, Long memberId) {
		return mapper.updateQuantity(quantity, id, memberId);
	}
}
