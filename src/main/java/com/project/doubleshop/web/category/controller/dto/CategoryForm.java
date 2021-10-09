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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryForm {

	// 카테고리 번호
	private Long id;

	// 카테고리 이름
	private String name;

	// 카테고리 타입
	private CategoryType categoryType;

	// 카테고리 레벨
	private DepthLevel depthLevel;

	// 환불가능 여부
	private Boolean isRefundable;

	// 카테고리 상태
	private Status status;

	// 카테고리 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;

	public CategoryForm(Category source) {
		this.id = source.getId();
		this.name = source.getName();
		this.categoryType = source.getCategoryType();
		this.depthLevel = source.getDepthLevel();
		this.isRefundable = source.getIsRefundable();
		this.status = source.getStatus();
		this.statusUpdateTime = source.getStatusUpdateTime();
	}
}
