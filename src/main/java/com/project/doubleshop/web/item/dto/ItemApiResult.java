package com.project.doubleshop.web.item.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.doubleshop.item.entity.Item;
import com.project.doubleshop.web.category.controller.dto.CategoryApiResult;

import lombok.Getter;

@Getter
public class ItemApiResult {
	// 상품 pk
	private final Long id;

	// 상품 이름
	private final String name;

	// 상품 한줄설명
	private final String description;

	// 브랜드 명
	private final String brandName;

	// 상품 가격
	private final Integer price;

	// 용량
	private final String volume;

	// 치수
	private final String dimension;

	// 포장타입
	private final String packageType;

	// 원산지
	private final String origin;

	// 유통기한
	private final String expiration;

	// 100g 당 가격
	private final Integer pricePer100g;

	// 알러지 정보
	private final String allergicInfo;

	// 상품 번호
	private final String modelSerialNo;

	// 상품 평가 점수
	private final Integer rating;

	// 검색 키워드
	private final String searchKeyword;

	// 총 재고수량
	private final Integer stock;

	// 할인가
	private final Integer discountPrice;

	// 작가
	private final String author;

	// 출판사
	private final String publisher;

	// ISBN
	private final String isbn;

	// image uri
	private final String imageUri;

	// 발행일
	private final LocalDate publishedTime;

	// 하루배송가능여부
	private final Boolean isOnedayEligible;

	// 당일배송가능여부
	private final Boolean isFreshEligible;

	// 등록된 시간
	private final LocalDateTime createTime;

	// 카테고리
	private final CategoryApiResult category;

	// rs
	private Long rowNumber;

	public Long getRowNumber() {
		return rowNumber;
	}

	public ItemApiResult(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
		this.brandName = item.getBrandName();
		this.price = item.getPrice();
		this.volume = item.getVolume();
		this.dimension = item.getDimension();
		this.packageType = item.getPackageType();
		this.origin = item.getOrigin();
		this.expiration = item.getExpiration();
		this.pricePer100g = item.getPricePer100g();
		this.allergicInfo = item.getAllergicInfo();
		this.modelSerialNo = item.getModelSerialNo();
		this.rating = item.getRating();
		this.searchKeyword = item.getSearchKeyword();
		this.stock = item.getStock();
		this.discountPrice = item.getDiscountPrice();
		this.author = item.getAuthor();
		this.publisher = item.getPublisher();
		this.isbn = item.getIsbn();
		this.imageUri = item.getImageUri();
		this.publishedTime = item.getPublishedTime();
		this.isOnedayEligible = item.getIsOnedayEligible();
		this.isFreshEligible = item.getIsFreshEligible();
		this.createTime = item.getCreateTime();
		this.category = new CategoryApiResult(item.getCategory());
	}
}
