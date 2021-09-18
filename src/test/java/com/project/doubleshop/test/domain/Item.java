package com.project.doubleshop.test.domain;

import lombok.Data;

@Data
public class Item {

    // 상품 pk
    private Long id;

    // 상품 이름
    private String name;

    // 상품 한줄설명
    private String description;

    // 상품 가격
    private Integer price;

    // 단위
    private String unit;

    // 용량
    private String volume;

    // 크기
    private String length;

    // 포장타입
    private String packageType;

    // 원산지
    private String certificateOfAnalysis;

    // 유통기한
    private String expiration;

    // 100g 당 가격
    private Integer pricePer100g;

    // 알러지 정보
    private String allergicIssue;

    // 상품 번호
    private Long modelSerialNo;

    // 상품 평가 점수
    private Integer ratePoint;

    // 검색 키워드
    private String searchKeyword;

    // 총 재고수량
    private Integer stock;

    // 할인가
    private Integer discountPrice;

    // 하루배송가능여부
    private boolean onedayEligible;

    // 당일배송가능여부
    private boolean freshEligible;

}