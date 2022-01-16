package com.project.doubleshop.domain.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.CartRepository;
import com.project.doubleshop.domain.member.service.AuthMemberService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	private final AuthMemberService authMemberService;

	public List<Cart> findCartsByMemberId(Long memberId) {
		return cartRepository.findCartsByMemberId(memberId);
	}

	@Transactional
	public Cart saveNewCart(Cart cart) {
		cartRepository.saveCart(cart);
		return cart;
	}

	public List<Cart> findCarts(Long memberId) {
		return cartRepository.findCartsByMemberId(memberId);
	}

	@Transactional
	public Integer deleteCarts(List<Long> cartIds) {
		return cartRepository.deleteCarts(cartIds);
	}
}
