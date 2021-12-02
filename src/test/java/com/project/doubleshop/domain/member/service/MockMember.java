package com.project.doubleshop.domain.member.service;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;

public class MockMember {
	public static class Member1 {
		public static final Long ID = 1L;
		public static final String USER_ID = "curry1028";
		public static final String PASSWORD = "$2a$12$wctB9qCE5y.r8qob/SPQMOpQaLT6ZGzMLcY1bM6j0JUonPMGr2H9e";
		public static final String NAME = "curry";
		public static final String EMAIL = "curry1028@gmail.com";
		public static final String PHONE = "010-1234-5678";

		public static final Member MEMBER = Member.builder()
			.id(ID)
			.userId(USER_ID)
			.password(PASSWORD)
			.name(NAME)
			.email(EMAIL)
			.phone(PHONE)
			.status(Status.ACTIVATED)
			.statusUpdateTime(LocalDateTime.now())
			.createTime(LocalDateTime.now())
			.count(0)
			.build();
	}

}
