package com.project.doubleshop.domain.item.repository.querydsl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.category.controller.dto.CategoryApiResult;

public class ItemQueryApiResult {
	// 상품 pk
	private Long id;

	// 상품 이름
	private String name;

	// 상품 한줄설명
	private String description;

	// 브랜드 명
	private String brandName;

	// 상품 가격
	private Integer price;

	// 용량
	private String volume;

	// 치수
	private String dimension;

	// 포장타입
	private String packageType;

	// 원산지
	private String origin;

	// 유통기한
	private String expiration;

	// 100g 당 가격
	private Integer pricePer100g;

	// 알러지 정보
	private String allergicInfo;

	// 상품 번호
	private String modelSerialNo;

	// 상품 평가 점수
	private Integer rating;

	// 검색 키워드
	private String searchKeyword;

	// 카테고리
	private Long categoryId;

	public ItemQueryApiResult(Long id, String name, String description, String brandName, Integer price, Integer rating, String searchKeyword, Long categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.brandName = brandName;
		this.price = price;
		this.rating = rating;
		this.searchKeyword = searchKeyword;
		this.categoryId = categoryId;
	}
}
