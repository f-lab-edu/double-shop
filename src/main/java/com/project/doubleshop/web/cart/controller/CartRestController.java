package com.project.doubleshop.web.cart.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.legacy.CartService;
import com.project.doubleshop.web.cart.dto.CartForm;
import com.project.doubleshop.web.cart.dto.CartDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("api")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;

	@GetMapping("member/{id}/cart")
	public ResponseEntity<List<CartDto>> findCarts(@PathVariable Long id) {
		List<Cart> carts = cartService.findCartsByMemberId(id);
		return ResponseEntity.ok(carts.stream().map(CartDto::new).collect(Collectors.toList()));
	}

	@PostMapping("member/{memberId}/cart/{itemId}")
	public ResponseEntity<CartDto> saveNewCart(@PathVariable Long memberId, @PathVariable Long itemId, @RequestParam Integer quantity) {
		Cart cart = Cart.builder()
			.memberId(memberId)
			.itemId(itemId)
			.quantity(quantity)
			.build();
		return ResponseEntity.ok(new CartDto(cartService.saveNewCart(cart)));
	}

	@PatchMapping("member/{id}/cart")
	public ResponseEntity<Integer> updateCart(@PathVariable Long id,
		@RequestBody CartForm cartForm) {
		return ResponseEntity.ok(cartService.updateCartQuantity(cartForm.getQuantity(), cartForm.getId(), id));
	}

	@DeleteMapping("member/{id}/cart")
	public ResponseEntity<Integer> deleteCart(@PathVariable Long id,
		@RequestBody Map<String, List<Long>> requestMap) {
		return ResponseEntity.ok(cartService.deleteCarts(id, requestMap.get("itemIds")));
	}
}
