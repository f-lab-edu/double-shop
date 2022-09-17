package com.project.doubleshop.domain.member;

public interface MemberAuthProcessor<T> {
	T login(String userId, String password);
}
