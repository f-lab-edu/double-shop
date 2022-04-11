package com.project.doubleshop.web.cart.dto;

import com.project.doubleshop.domain.cart.entity.Cart;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.web.item.dto.ItemApiResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CartDto {
	private Long id;
	private Long memberId;
	private CartItem item;
	private int quantity;

	public CartDto(Cart source) {
		this.id = source.getId();
		this.memberId = source.getMember().getId();
		this.item = new CartItem(source.getItem());
		this.quantity = source.getQuantity();
	}
}
