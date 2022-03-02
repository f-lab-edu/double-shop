package com.project.doubleshop.domain.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.CartRepository;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.utils.ExceptionUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
	private final ItemService itemService;
	private final CartRepository cartRepository;

	@Transactional
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	public Cart findById(Long cartId) {
		return cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException(String.format("Cart id '%d' not found.", cartId)));
	}

	public List<Cart> findCartsByMemberId(Long memberId) {
		return cartRepository.findCartsByMemberId(memberId);
	}

	public void findAllByIds(List<Long> cartIds, Long memberId) {
		List<Cart> carts = cartRepository.findAllById(cartIds);
		Map<Long, Cart> cartMap = carts
			.stream()
			.collect(Collectors.toMap(Cart::getId, Function.identity()));

		List<Long> invalidIds = cartIds
			.stream()
			.filter(id -> !cartMap.containsKey(id))
			.filter(id -> !cartMap.get(id).getMemberId().equals(memberId))
			.collect(Collectors.toList());

		ExceptionUtils.findInvalidIdsAndThrow400Error(invalidIds, "Invalid cart id");
	}

	@Transactional
	public Integer deleteCarts(List<Long> cartIds, Long memberId) {
		findAllByIds(cartIds, memberId);
		cartRepository.deleteAllById(cartIds);
		return cartIds.size();
	}
}
