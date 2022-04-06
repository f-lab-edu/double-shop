package com.project.doubleshop.domain.category.entity;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.StatusConverter;
import com.project.doubleshop.domain.common.StatusManager;
import com.project.doubleshop.web.category.controller.dto.CategoryForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "l2Cache")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category implements StatusManager {
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
	@Convert(converter = StatusConverter.class)
	private Status status;

	// 카테고리 상태 업데이트 시간
	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
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
	public void saveStatus(Status status) {
		this.status = status;
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
