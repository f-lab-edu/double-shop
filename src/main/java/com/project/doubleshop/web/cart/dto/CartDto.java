package com.project.doubleshop.web.cart.dto;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CartDto {
	private Long id;
	private Member member;
	private Item item;
	private int quantity;

	public CartDto(Cart source) {
		this.id = source.getId();
		this.member = source.getMember();
		this.item = source.getItem();
		this.quantity = source.getQuantity();
	}
}
