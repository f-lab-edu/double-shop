package com.project.doubleshop.cart.service;

import com.project.doubleshop.cart.entity.Cart;
import com.project.doubleshop.item.MockItem;
import com.project.doubleshop.member.service.MockMember;

public class MockCart {
	public static class Cart1 {
		public static final Long ID = 1L;
		public static final Integer QUANTITY = 1;

		public static final Cart CART = Cart.builder()
			.id(ID)
			.member(MockMember.Member1.MEMBER)
			.item(MockItem.Item1.ITEM)
			.quantity(QUANTITY)
			.build();

		public static final Cart NEW_CART = Cart.builder()
			.member(MockMember.Member1.MEMBER)
			.item(MockItem.Item1.ITEM)
			.quantity(QUANTITY)
			.build();
	}
}
