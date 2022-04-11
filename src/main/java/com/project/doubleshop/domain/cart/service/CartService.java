package com.project.doubleshop.domain.cart.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.CartRepository;
import com.project.doubleshop.domain.exception.BadRequestException;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.MemberService;
import com.project.doubleshop.domain.utils.ExceptionUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
	private final ItemService itemService;
	private final CartRepository cartRepository;
	private final MemberService memberService;

	@Transactional
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	public Cart findByIdAndMemberId(Long cartId, Long memberId) {
		return cartRepository.findCartByIdAndMemberId(cartId, memberId).orElseThrow(() -> new NotFoundException(String.format("Cart id '%d' not found.", cartId)));
	}

	public List<Cart> findCartsByMemberId(Long memberId) {
		return cartRepository.findCartsByMemberId(memberId);
	}

	public void findAllByIds(List<Long> cartIds, Long memberId) {
		List<Cart> carts = cartRepository.findCartsByIdsAndMemberId(cartIds, memberId);
		if (cartIds.size() != carts.size()) {
			Map<Long, Cart> cartMap = carts
				.stream()
				.collect(Collectors.toMap(Cart::getId, Function.identity()));


			List<Long> invalidIds = cartIds
				.stream()
				.filter(id -> !cartMap.containsKey(id))
				.filter(id -> !cartMap.get(id).getMember().getId().equals(memberId))
				.collect(Collectors.toList());

			ExceptionUtils.findInvalidIdsAndThrow400Error(invalidIds, "Invalid cart id");
		}
	}

	@Transactional
	public Integer updateCartQuantity(Integer quantity, Long cartId, Long memberId) {
		Cart cart = findByIdAndMemberId(cartId, memberId);
		Item item = cart.getItem();

		Integer stock = item.getStock();
		if (stock < quantity) {
			throw new BadRequestException(String.format("Cart quantity [%d] must not exceed the item stock [%d]", quantity, stock));
		}
		cart.setQuantity(quantity);
		return cart.getQuantity();
	}

	@Transactional
	public Integer deleteCarts(List<Long> cartIds, Long memberId) {
		findAllByIds(cartIds, memberId);
		cartRepository.deleteAllById(cartIds);
		return cartIds.size();
	}

	@Transactional
	public Cart newCart(Long memberId, Long itemId, Integer quantity) {
		Member member = memberService.findById(memberId);
		Item item = itemService.findById(itemId);
		Cart cart = cartRepository.save(new Cart(member, item, quantity));
		cart.setItem(item);
		return cart;
	}
}
