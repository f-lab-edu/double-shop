package com.project.doubleshop.domain.order;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;
import com.project.doubleshop.domain.member.service.MockMember;
import com.project.doubleshop.domain.order.entity.Order;

public class MockOrder {
	public static class Order1 {
		public static final Long ID = 1L;

		public static final Member MEMBER = MockMember.Member1.MEMBER;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.member(MEMBER)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();
	}

	public static class Order2 {
		public static final Long ID = 2L;

		public static final Member MEMBER = MockMember.Member2.MEMBER;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.member(MEMBER)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();
	}

	public static class Order3 {
		public static final Long ID = 3L;

		public static final Member MEMBER = MockMember.Member3.MEMBER;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.member(MEMBER)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();
	}

	public static class Order4 {
		public static final Long ID = 4L;

		public static final Member MEMBER = MockMember.Member4.MEMBER;

		public static final LocalDateTime ORDERED_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2022, 1, 6, 12, 32, 10);

		public static final Order ORDER = Order.builder()
			.id(ID)
			.member(MEMBER)
			.orderedTime(ORDERED_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();
	}
}
