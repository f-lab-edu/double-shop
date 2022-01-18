package com.project.doubleshop.domain.cart.service;

import com.project.doubleshop.domain.cart.entity.Cart;

public class MockCart {
	public static class Cart1 {
		public static final Long ID = 1L;
		public static final Long MEMBER_ID = 1L;
		public static final Long ITEM_ID = 1L;
		public static final Integer QUANTITY = 1;

		public static final Cart CART = Cart.builder()
			.id(ID)
			.memberId(MEMBER_ID)
			.itemId(ITEM_ID)
			.quantity(QUANTITY)
			.build();
	}
}
