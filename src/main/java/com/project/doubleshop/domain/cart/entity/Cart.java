package com.project.doubleshop.domain.cart.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Cart {
	private Long id;

	private Long memberId;

	private Long itemId;

	private Integer quantity;

	public Cart(Long memberId, Long itemId, Integer quantity) {
		this.memberId = memberId;
		this.itemId = itemId;
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "Cart{" +
			"id=" + id +
			", memberId=" + memberId +
			", itemId=" + itemId +
			", quantity=" + quantity +
			'}';
	}
}
