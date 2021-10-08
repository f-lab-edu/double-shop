package com.project.doubleshop.domain.category.entity;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

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

	// 카테고리 상태
	private Status status;

	// 카테고리 상태 업데이트 시간
	private LocalDateTime statusUpdateTime;
}
