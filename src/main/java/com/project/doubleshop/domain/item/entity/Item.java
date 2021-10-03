package com.project.doubleshop.domain.item.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Item {

    // 상품 pk
    private Long id;

    // 상품 이름
    @NotBlank(message = "field 'name' must be provided.")
    private String name;

    // 상품 한줄설명
    @NotBlank(message = "field 'description' must be provided.")
    private String description;

    // 브랜드 명
    @NotBlank(message = "field 'brandName' must be provided.")
    private String brandName;

    // 상품 가격
    @Range(min = 1000, max = 10000000, message = "field 'price' must be between 1,000 and 10,000,000.")
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

    // 발행일
    private LocalDate publishedTime;

    // 하루배송가능여부
    private boolean isOnedayEligible;

    // 당일배송가능여부
    private boolean isFreshEligible;

    // 상태
    private Status status;

    // 상태 업데이트 시간
    private LocalDateTime statusUpdateTime;

    // 등록된 시간
    private LocalDateTime createTime;

    // 상품 인스턴스 생성 로직
    public static Item convertToItem(ItemForm dto) {
        return Item.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .brandName(dto.getBrandName())
            .price(dto.getPrice())
            .volume(dto.getVolume())
            .dimension(dto.getDimension())
            .packageType(dto.getPackageType())
            .origin(dto.getOrigin())
            .expiration(dto.getExpiration())
            .pricePer100g(dto.getPricePer100g())
            .allergicInfo(dto.getAllergicInfo())
            .modelSerialNo(dto.getModelSerialNo())
            .rating(dto.getRating())
            .searchKeyword(dto.getSearchKeyword())
            .stock(dto.getStock())
            .discountPrice(dto.getDiscountPrice())
            .author(dto.getAuthor())
            .publisher(dto.getPublisher())
            .isbn(dto.getIsbn())
            .publishedTime(dto.getPublishedTime())
            .isOnedayEligible(dto.isOnedayEligible())
            .isFreshEligible(dto.isFreshEligible())
            .build();
    }

    public void removeStock(int stock) {
        this.stock -= stock;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }
}