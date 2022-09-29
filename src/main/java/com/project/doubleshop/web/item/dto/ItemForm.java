package com.project.doubleshop.web.item.dto;

import java.time.LocalDate;

import com.project.doubleshop.item.domain.Item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemForm {
	// 상품 id
	private Long id;

	// 카테고리
	private Long categoryId;

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

	// 총 재고수량
	private Integer stock;

	// 할인가
	private Integer discountPrice;

	// 작가
	private String author;

	// 출판사
	private String publisher;

	// ISBN
	private String isbn;

	private String imageUrl;

	// 발행일
	private LocalDate publishedTime;

	// 하루배송가능여부
	private Boolean isOnedayEligible;

	// 당일배송가능여부
	private Boolean isFreshEligible;

	public ItemForm(Item source) {
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
		this.imageUrl = source.getImageUri();
		this.publishedTime = source.getPublishedTime();
		this.isOnedayEligible = source.getIsOnedayEligible();
		this.isFreshEligible = source.getIsFreshEligible();
		this.categoryId = source.getCategory().getId();
	}
}
