package com.project.doubleshop.cart.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.doubleshop.cart.entity.Cart;
import com.project.doubleshop.cart.repository.CartRepository;
import com.project.doubleshop.cart.repository.querydsl.CartQueryResult;
import com.project.doubleshop.exception.BadRequestException;
import com.project.doubleshop.exception.NotFoundException;
import com.project.doubleshop.item.entity.Item;
import com.project.doubleshop.item.service.ItemService;
import com.project.doubleshop.member.domain.Member;
import com.project.doubleshop.member.application.MemberService;
import com.project.doubleshop.utils.ExceptionUtils;
import com.project.doubleshop.web.cart.dto.CartDto;
import com.project.doubleshop.web.cart.dto.CartItem;

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

	public List<CartDto> findCartsWithItemsByMemberId(Long memberId) {
		List<CartQueryResult> cartsJoinWithItem = cartRepository.findCartsJoinWithItem(memberId);
		return cartsJoinWithItem.stream().map(c -> new CartDto(
			c.getId(),
			c.getMemberId(),
			new CartItem(c),
			c.getQuantity()
		)).collect(Collectors.toList());
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
	@CacheEvict(value = "cartsForMember", key = "#memberId")
	public Cart newCart(Long memberId, Long itemId, Integer quantity) {
		Member member = memberService.findById(memberId);
		Item item = itemService.findById(itemId);
		Cart cart = cartRepository.save(new Cart(member, item, quantity));
		cart.setItem(item);
		return cart;
	}
} 
