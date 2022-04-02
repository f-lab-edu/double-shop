package com.project.doubleshop.domain.address.mapper;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.address.entity.Address;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.service.MockMember;

public class MockAddress {
	public static class Address1 {
		public static final Long ID = 1L;

		public static final String CITY = "서울";

		public static final String ZIPCODE = "012345";

		public static final String DETAIL = "어딘가에 있는 아파트";

		public static final Status STATUS = Status.ACTIVATED;

		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2021, 10, 13, 9, 27, 1);

		public static final Long MEMBER_ID = MockMember.Member1.ID;

		public static final Address ADDRESS_1 = Address.builder()
			.id(ID)
			.city(CITY)
			.zipcode(ZIPCODE)
			.detail(DETAIL)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.member(MockMember.Member1.MEMBER)
			.build();

		public static final Address ADDRESS_1_FORM = Address.builder()
			.city(CITY)
			.zipcode(ZIPCODE)
			.detail(DETAIL)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.member(MockMember.Member1.MEMBER)
			.build();
	}
}
