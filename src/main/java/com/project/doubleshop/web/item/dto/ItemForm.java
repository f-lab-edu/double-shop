package com.project.doubleshop.web.item.dto;

import java.time.LocalDate;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.item.entity.Item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemForm {
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

	// 단위
	private String unit;

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

	// 발행일
	private LocalDate publishedTime;

	// 하루배송가능여부
	private boolean isOnedayEligible;

	// 당일배송가능여부
	private boolean isFreshEligible;

	public ItemForm(Item source) {
		this.id = source.getId();
		this.name = source.getName();
		this.description = source.getDescription();
		this.brandName = source.getBrandName();
		this.price = source.getPrice();
		this.unit = source.getUnit();
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
		this.isOnedayEligible = source.isOnedayEligible();
		this.isFreshEligible = source.isFreshEligible();
	}
}
