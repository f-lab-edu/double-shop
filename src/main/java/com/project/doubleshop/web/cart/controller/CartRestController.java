package com.project.doubleshop.web.cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.cart.service.CartService;
import com.project.doubleshop.web.cart.dto.CartForm;
import com.project.doubleshop.web.cart.dto.CartDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("api")
@RestController
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;

	@GetMapping(value = "member/{id}/cart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartDto>> findCarts(@PathVariable Long id) {
		return ResponseEntity.ok(cartService.findCartsWithItemsByMemberId(id));
	}

	@PostMapping("member/{memberId}/cart/{itemId}")
	public ResponseEntity<CartDto> saveNewCart(@PathVariable Long memberId, @PathVariable Long itemId, @RequestParam Integer quantity) {
		return ResponseEntity.ok(new CartDto(cartService.newCart(memberId, itemId, quantity)));
	}

	@PatchMapping("member/{id}/cart")
	public ResponseEntity<Integer> updateCart(@PathVariable Long id,
		@RequestBody CartForm cartForm) {
		return ResponseEntity.ok(cartService.updateCartQuantity(cartForm.getQuantity(), cartForm.getId(), id));
	}

	@DeleteMapping("member/{id}/cart")
	public ResponseEntity<Integer> deleteCart(@PathVariable Long id,
		@RequestBody Map<String, List<Long>> requestMap) {
		return ResponseEntity.ok(cartService.deleteCarts(requestMap.get("cartIds"), id));
	}
}
