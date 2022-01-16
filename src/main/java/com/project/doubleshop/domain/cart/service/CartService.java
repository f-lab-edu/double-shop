package com.project.doubleshop.domain.cart.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.CartRepository;
import com.project.doubleshop.web.item.exception.InvalidArgumentException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	public List<Cart> findCartsByMemberId(Long memberId) {
		return cartRepository.findCartsByMemberId(memberId);
	}

	@Transactional
	public Cart saveNewCart(Cart cart) {
		cartRepository.saveCart(cart);
		return cart;
	}

	@Transactional
	public Integer deleteCarts(Long memberId, List<Long> cartIds) {
		List<Cart> carts = cartRepository.findCartInIds(cartIds, memberId);
		if (cartIds.size() != carts.size()) {
			// 파라미터로 전달 받은 id들 중에서 검색이 안된 장바구니가 있다면, 예외를 던진다.
			findInvalidIdsAndThrowException(carts, cartIds);
		}
		return cartRepository.deleteCarts(memberId, cartIds);
	}

	private void findInvalidIdsAndThrowException(List<Cart> carts, List<Long> cartIds) {
		StringBuilder sb = new StringBuilder();

		// 검색결과로 받은 장바구니 id 들을 수집하여, 파라미터로 전달된 장바구니 id 중에서 검색이 안된 장바구니 id를 예외로 전달한다.
		Set<Long> validIds = carts
			.stream()
			.map(Cart::getId)
			.collect(Collectors.toSet());
		List<Long> invalidIds = cartIds
			.stream()
			.filter(id -> !validIds.contains(id))
			.collect(Collectors.toList());

		sb.append("Invalid cart id [");
		for (int i = 0; i < invalidIds.size(); i++) {
			sb.append(invalidIds.get(i));
			if (i < invalidIds.size() - 1) {
				sb.append(", ");
			} else {
				sb.append("]");
			}
		}

		throw new InvalidArgumentException(sb.toString());
	}
}
