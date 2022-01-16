package com.project.doubleshop.web.cart.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.cart.service.CartService;
import com.project.doubleshop.web.cart.dto.CartDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;

	@GetMapping("member/{id}/cart")
	public ResponseEntity<List<CartDto>> findCarts(@PathVariable Long id) {
		List<Cart> carts = cartService.findCartsByMemberId(id);
		return ResponseEntity.ok(carts.stream().map(CartDto::new).collect(Collectors.toList()));
	}
}
