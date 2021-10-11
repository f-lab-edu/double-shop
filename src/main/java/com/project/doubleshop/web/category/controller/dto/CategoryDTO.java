package com.project.doubleshop.web.category.controller.dto;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.category.entity.CategoryType;
import com.project.doubleshop.domain.category.entity.DepthLevel;
import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CategoryDTO {

	// 카테고리 pk
	private Long id;

	// 카테고리 이름
	private String name;

	// 카테고리 타입
	private CategoryType categoryType;

	// 카테고리 레벨
	private DepthLevel depthLevel;

	// 환불가능 여부
	private Boolean isRefundable;

	public CategoryDTO(Category source) {
		this.id = source.getId();
		this.name = source.getName();
		this.categoryType = source.getCategoryType();
		this.depthLevel = source.getDepthLevel();
		this.isRefundable = source.getIsRefundable();
	}
}
