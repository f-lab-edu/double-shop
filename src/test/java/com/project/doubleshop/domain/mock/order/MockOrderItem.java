package com.project.doubleshop.domain.mock.order;

import static com.project.doubleshop.domain.mock.item.MockItem.*;
import static com.project.doubleshop.domain.mock.order.MockOrder.*;

import com.project.doubleshop.domain.item.service.MockItem;
import com.project.doubleshop.domain.order.entity.mock.OrderItem;

public class MockOrderItem {
	public static class OrderItem1 {

		public static final Long ORDER_ID = Order1.ID;

		public static final Long ITEM_ID = MockItem.Item1.ID;

		public static final Integer COUNT = 2;

		public static final Integer PRICE = MockItem.Item1.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem2 {

		public static final Long ORDER_ID = Order1.ID;

		public static final Long ITEM_ID = Item2.ID;

		public static final Integer COUNT = 4;

		public static final Integer PRICE = Item2.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem3 {

		public static final Long ORDER_ID = Order1.ID;

		public static final Long ITEM_ID = Item3.ID;

		public static final Integer COUNT = 5;

		public static final Integer PRICE = Item3.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem4 {

		public static final Long ORDER_ID = Order1.ID;

		public static final Long ITEM_ID = Item4.ID;

		public static final Integer COUNT = 4;

		public static final Integer PRICE = Item4.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem5 {

		public static final Long ORDER_ID = Order2.ID;

		public static final Long ITEM_ID = Item5.ID;

		public static final Integer COUNT = 2;

		public static final Integer PRICE = Item5.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem6 {

		public static final Long ORDER_ID = Order2.ID;

		public static final Long ITEM_ID = Item6.ID;

		public static final Integer COUNT = 2;

		public static final Integer PRICE = Item6.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem7 {

		public static final Long ORDER_ID = Order2.ID;

		public static final Long ITEM_ID = Item7.ID;

		public static final Integer COUNT = 3;

		public static final Integer PRICE = Item7.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem8 {

		public static final Long ORDER_ID = Order2.ID;

		public static final Long ITEM_ID = Item8.ID;

		public static final Integer COUNT = 3;

		public static final Integer PRICE = Item8.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem9 {

		public static final Long ORDER_ID = Order3.ID;

		public static final Long ITEM_ID = Item9.ID;

		public static final Integer COUNT = 3;

		public static final Integer PRICE = Item9.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem10 {

		public static final Long ORDER_ID = Order3.ID;

		public static final Long ITEM_ID = Item10.ID;

		public static final Integer COUNT = 3;

		public static final Integer PRICE = Item10.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem11 {

		public static final Long ORDER_ID = Order4.ID;

		public static final Long ITEM_ID = Item11.ID;

		public static final Integer COUNT = 5;

		public static final Integer PRICE = Item11.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}

	public static class OrderItem12 {

		public static final Long ORDER_ID = Order4.ID;

		public static final Long ITEM_ID = Item12.ID;

		public static final Integer COUNT = 5;

		public static final Integer PRICE = Item12.PRICE;

		public static final OrderItem ORDER_ITEM = OrderItem.builder()
			.orderId(ORDER_ID)
			.itemId(ITEM_ID)
			.count(COUNT)
			.price(PRICE)
			.build();
	}
}
