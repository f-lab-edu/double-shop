package com.project.doubleshop.domain.category.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {
	// 카테고리 pk
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 카테고리 이름
	private String name;

	// 카테고리 타입
	@Enumerated(EnumType.STRING)
	private CategoryType categoryType;

	// 카테고리 레벨
	@Enumerated(EnumType.STRING)
	private DepthLevel depthLevel;

	// 환불가능 여부
	private Boolean isRefundable;

	// 카테고리 상태
	private Status status;

	// 카테고리 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;

	// 카테고리 인스턴스 생성 로직
	public static Category convertToCategory(CategoryForm form) {
		return Category.builder()
			.id(form.getId())
			.name(form.getName())
			.categoryType(form.getCategoryType())
			.depthLevel(form.getDepthLevel())
			.isRefundable(form.getIsRefundable())
			.status(form.getStatus())
			.statusUpdateTime(form.getStatusUpdateTime())
			.build();
	}

	@Override
	public String toString() {
		return "Category{" +
			"id=" + id +
			", name='" + name + '\'' +
			", categoryType=" + categoryType +
			", depthLevel=" + depthLevel +
			", isRefundable=" + isRefundable +
			", status=" + status +
			", statusUpdateTime=" + statusUpdateTime +
			'}';
	}
}
