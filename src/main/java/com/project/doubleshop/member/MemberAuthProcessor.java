package com.project.doubleshop.member;

public interface MemberAuthProcessor<T> {
	T login(String userId, String password);
}
