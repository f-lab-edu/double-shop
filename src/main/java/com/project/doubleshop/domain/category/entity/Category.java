package com.project.doubleshop.domain.category.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Category {
	// 카테고리 pk
	private Long id;

	// 카테고리 이름
	private String name;

	// 카테고리 타입
	private CategoryType categoryType;

	// 카테고리 레벨
	private DepthLevel depthLevel;

	// 환불가능 여부
	private boolean isRefundable;

	@Override
	public String toString() {
		return "Category{" +
			"id=" + id +
			", name='" + name + '\'' +
			", categoryType=" + categoryType +
			", depthLevel=" + depthLevel +
			", isRefundable=" + isRefundable +
			'}';
	}
}
