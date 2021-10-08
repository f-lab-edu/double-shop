package com.project.doubleshop.domain.category.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Category {
	// 카테고리 pk
	private Long id;

	// 카테고리 이름
	private String name;

	// 부모카테고리 이름
	private Long parentId;

	// 카테고리 레벨
	private DepthLevel depthLevel;

	// 환불가능 여부
	private boolean isRefundable;
}
