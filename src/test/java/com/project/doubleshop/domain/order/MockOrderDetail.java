package com.project.doubleshop.domain.order;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.MockItem;
import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.entity.OrderDetail;

public class MockOrderDetail {
	public static class OrderDetail1 {
		public static final Order ORDER = MockOrder.Order1.ORDER;
		public static final Item ITEM = MockItem.Item1.ITEM;
		public static final Integer QUANTITY = 1;
		public static final Integer PRICE = 1000;
		public static final Status STATUS = Status.ACTIVATED;
		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final OrderDetail ORDER_DETAIL = OrderDetail.builder()
			.order(ORDER)
			.item(ITEM)
			.quantity(QUANTITY)
			.price(PRICE)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();
	}
}
