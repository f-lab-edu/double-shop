package com.project.doubleshop.domain.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.repository.CartRepository;
import com.project.doubleshop.domain.exception.BadRequestException;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.item.service.ItemService;
import com.project.doubleshop.domain.utils.ExceptionUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

	private final ItemService itemService;
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
		findCartsInCartIds(cartIds, memberId);
		return cartRepository.deleteCarts(memberId, cartIds);
	}

	public List<Cart> findCartsInCartIds(List<Long> cartIds, Long memberId) {
		List<Cart> carts = cartRepository.findCartInIds(cartIds, memberId);
		if (cartIds.size() != carts.size()) {
			// 파라미터로 전달 받은 id들 중에서 검색이 안된 장바구니가 있다면, 예외를 던진다.
			// 검색결과로 받은 장바구니 id 들을 수집하여, 파라미터로 전달된 장바구니 id 중에서 검색이 안된 장바구니 id를 예외로 전달한다.
			Set<Long> validIds = carts
				.stream()
				.map(Cart::getId)
				.collect(Collectors.toSet());

			List<Long> invalidIds = cartIds
				.stream()
				.filter(id -> !validIds.contains(id))
				.collect(Collectors.toList());

			ExceptionUtils.findInvalidIdsAndThrow404Error(invalidIds, "Invalid cart id");
		}
		return carts;
	}

	@Transactional
	public Integer updateCartQuantity(Integer quantity, Long cartId, Long memberId) {

		Cart cart = findByCartIdAndMemberId(cartId, memberId)
			.orElseThrow(() -> new NotFoundException(String.format("Cart id [%d] not found.", cartId)));

		Long itemId = cart.getItemId();
		Item item = itemService.findItemById(itemId)
			.orElseThrow(() -> new NotFoundException(String.format("Item id [%d] not found.", itemId)));

		Integer stock = item.getStock();
		if (stock < quantity) {
			throw new BadRequestException(String.format("Cart quantity [%d] must not exceed the item stock [%d]", quantity, stock));
		}

		return cartRepository.updateCarts(quantity, cartId, memberId);
	}

	public Optional<Cart> findByCartIdAndMemberId(Long cartId, Long memberId) {
		return Optional.of(cartRepository.findCartById(cartId, memberId));
	}
}
