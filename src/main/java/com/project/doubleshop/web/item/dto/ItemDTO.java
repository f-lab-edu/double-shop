package com.project.doubleshop.web.item.dto;

import java.time.LocalDate;

import com.project.doubleshop.item.domain.Item;

import lombok.Getter;

@Getter
public class ItemDTO {
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

	// 발행일
	private final LocalDate publishedTime;

	// 하루배송가능여부
	private final Boolean isOnedayEligible;

	// 당일배송가능여부
	private final Boolean isFreshEligible;

	public ItemDTO(Item source) {
		this.id = source.getId();
		this.name = source.getName();
		this.description = source.getDescription();
		this.brandName = source.getBrandName();
		this.price = source.getPrice();
		this.volume = source.getVolume();
		this.dimension = source.getDimension();
		this.packageType = source.getPackageType();
		this.origin = source.getOrigin();
		this.expiration = source.getExpiration();
		this.pricePer100g = source.getPricePer100g();
		this.allergicInfo = source.getAllergicInfo();
		this.modelSerialNo = source.getModelSerialNo();
		this.rating = source.getRating();
		this.searchKeyword = source.getSearchKeyword();
		this.stock = source.getStock();
		this.discountPrice = source.getDiscountPrice();
		this.author = source.getAuthor();
		this.publisher = source.getPublisher();
		this.isbn = source.getIsbn();
		this.publishedTime = source.getPublishedTime();
		this.isOnedayEligible = source.getIsOnedayEligible();
		this.isFreshEligible = source.getIsFreshEligible();
	}
}
