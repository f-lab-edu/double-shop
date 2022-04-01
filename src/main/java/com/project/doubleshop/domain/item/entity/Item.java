package com.project.doubleshop.domain.item.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Range;

import com.project.doubleshop.domain.category.entity.Category;
import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.common.StatusConverter;
import com.project.doubleshop.domain.common.StatusManager;
import com.project.doubleshop.web.item.dto.ItemForm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item implements StatusManager {

    // 상품 pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

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
    @Range(min = 1000, max = 10000000, message = "field 'price' must be between 1000 and 10000000")
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
    @Min(0)
    private Integer rating;

    // 검색 키워드
    private String searchKeyword;

    // 총 재고수량
    @Range(min = 0, message = "field 'stock' must have more than 0")
    private Integer stock;

    // 할인가
    @Min(0)
    private Integer discountPrice;

    // 작가
    private String author;

    // 출판사
    private String publisher;

    // ISBN
    private String isbn;

    // 이미지 url
    private String imageUri;

    // 발행일
    @PastOrPresent(message = "field 'publishedTime' must be present or past")
    private LocalDate publishedTime;

    // 하루배송가능여부
    private Boolean isOnedayEligible;

    // 당일배송가능여부
    private Boolean isFreshEligible;

    // 상태
    @Convert(converter = StatusConverter.class)
    private Status status;

    // 상태 업데이트 시간
    @PastOrPresent(message = "field 'statusUpdateTime' must be present or past")
    @Column(insertable = false, updatable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime statusUpdateTime;

    // 등록된 시간
    @PastOrPresent(message = "field 'createTime' must be present or past")
    @Column(insertable = false, updatable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @Override
    public void saveStatus(Status status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImageUri(String imageUrl) {
        this.imageUri = imageUrl;
    }

    public void decreaseStock(int stock) {
        validateStockParameter(stock);
        this.stock -= stock;
    }

    public void decreaseStock() {
        decreaseStock(1);
    }

    public void increaseStock(int stock) {
        validateStockParameter(stock);
        this.stock += stock;
    }

    public void increaseStock() {
        increaseStock(1);
    }

    private void validateStockParameter(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(
                String.format(
                    "com.project.doubleshop.domain.item.entity.decreaseStock(%d), parameter 'stock' must have positive integer value.",
                    stock)
            );
        }
    }

    // 상품 인스턴스 생성 로직
    public static Item convertToItem(ItemForm form) {
        return Item.builder()
            .id(form.getId())
            .name(form.getName())
            .description(form.getDescription())
            .brandName(form.getBrandName())
            .price(form.getPrice())
            .volume(form.getVolume())
            .dimension(form.getDimension())
            .packageType(form.getPackageType())
            .origin(form.getOrigin())
            .expiration(form.getExpiration())
            .pricePer100g(form.getPricePer100g())
            .allergicInfo(form.getAllergicInfo())
            .modelSerialNo(form.getModelSerialNo())
            .rating(form.getRating())
            .searchKeyword(form.getSearchKeyword())
            .stock(form.getStock())
            .discountPrice(form.getDiscountPrice())
            .author(form.getAuthor())
            .publisher(form.getPublisher())
            .isbn(form.getIsbn())
            .publishedTime(form.getPublishedTime())
            .isOnedayEligible(form.getIsOnedayEligible())
            .isFreshEligible(form.getIsFreshEligible())
            .build();
    }
}