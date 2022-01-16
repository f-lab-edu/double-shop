package com.project.doubleshop.web.cart.dto;

import com.project.doubleshop.domain.cart.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartDto {
	private final Long id;
	private final Long memberId;
	private final Long itemId;

	public CartDto(Cart source) {
		this.id = source.getId();
		this.memberId = source.getMemberId();
		this.itemId = source.getItemId();
	}
}
