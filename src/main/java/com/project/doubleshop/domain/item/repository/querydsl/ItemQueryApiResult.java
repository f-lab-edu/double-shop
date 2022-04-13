package com.project.doubleshop.domain.item.repository.querydsl;

import com.project.doubleshop.domain.item.entity.Item;

import lombok.Getter;

@Getter
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

	// 상품 평가 점수
	private Integer rating;

	// 검색 키워드
	private String searchKeyword;

	// 카테고리
	private Long categoryId;

	public ItemQueryApiResult() {
	}

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
