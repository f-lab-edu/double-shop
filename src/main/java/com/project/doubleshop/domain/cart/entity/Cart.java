package com.project.doubleshop.domain.cart.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Cart {
	private Long id;

	private Long memberId;

	private Long itemId;

	public Cart(Long memberId, Long itemId) {
		this.memberId = memberId;
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "Cart{" +
			"id=" + id +
			", memberId=" + memberId +
			", itemId=" + itemId +
			'}';
	}
}
