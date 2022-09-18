package com.project.doubleshop.web.category.controller.dto;

import com.project.doubleshop.category.entity.Category;
import com.project.doubleshop.category.entity.CategoryType;
import com.project.doubleshop.category.entity.DepthLevel;

import lombok.Getter;

@Getter
public class CategoryApiResult {

	// 카테고리 pk
	private final Long id;

	// 카테고리 이름
	private final String name;

	// 카테고리 타입
	private final CategoryType categoryType;

	// 카테고리 레벨
	private final DepthLevel depthLevel;

	// 환불가능 여부
	private final Boolean isRefundable;

	public CategoryApiResult(Category source) {
		this.id = source.getId();
		this.name = source.getName();
		this.categoryType = source.getCategoryType();
		this.depthLevel = source.getDepthLevel();
		this.isRefundable = source.getIsRefundable();
	}
}
