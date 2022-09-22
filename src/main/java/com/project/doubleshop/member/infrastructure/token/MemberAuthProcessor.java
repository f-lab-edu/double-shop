package com.project.doubleshop.member.infrastructure.token;

public interface MemberAuthProcessor<T> {
	T login(String userId, String password);
}
