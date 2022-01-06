package com.project.doubleshop.domain.mock.order;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.service.MockMember;
import com.project.doubleshop.domain.order.entity.mock.Order;

public class MockOrder {
	public static class Order1 {
		public static final Long ID = 1L;

		public static final Long MEMBER_ID = MockMember.Member1.ID;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.memberId(MEMBER_ID)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.createTime(CREATE_TIME)
			.build();
	}

	public static class Order2 {
		public static final Long ID = 2L;

		public static final Long MEMBER_ID = MockMember.Member2.ID;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.memberId(MEMBER_ID)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.createTime(CREATE_TIME)
			.build();
	}

	public static class Order3 {
		public static final Long ID = 3L;

		public static final Long MEMBER_ID = MockMember.Member3.ID;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.memberId(MEMBER_ID)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.createTime(CREATE_TIME)
			.build();
	}

	public static class Order4 {
		public static final Long ID = 4L;

		public static final Long MEMBER_ID = MockMember.Member4.ID;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.memberId(MEMBER_ID)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.createTime(CREATE_TIME)
			.build();
	}
}
