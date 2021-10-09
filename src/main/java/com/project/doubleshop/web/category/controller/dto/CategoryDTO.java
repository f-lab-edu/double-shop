package com.project.doubleshop.web.category.controller.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.entity.CategoryType;
import com.project.doubleshop.domain.category.entity.DepthLevel;
import com.project.doubleshop.domain.common.Status;

import lombok.Getter;

@Getter
public class CategoryDTO {

	// 카테고리 pk
	private final Long id;

	// 카테고리 이름
	private final String name;

	// 카테고리 타입
	private final CategoryType categoryType;

	// 카테고리 레벨
	private final DepthLevel depthLevel;

	// 환불가능 여부
	private final boolean isRefundable;

	// 카테고리 상태
	private final Status status;

	// 카테고리 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;

	public CategoryDTO(Category source) {
		this.id = source.getId();
		this.name = source.getName();
		this.categoryType = source.getCategoryType();
		this.depthLevel = source.getDepthLevel();
		this.isRefundable = source.isRefundable();
		this.status = source.getStatus();
	}
}
