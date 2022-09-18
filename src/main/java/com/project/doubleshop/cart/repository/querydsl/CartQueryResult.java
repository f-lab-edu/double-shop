package com.project.doubleshop.cart.repository.querydsl;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartQueryResult {
	private Long id;
	private Long memberId;
	private Long itemId;
	private String itemName;
	private String brandName;
	private String categoryName;
	private Integer itemPrice;
	private Integer quantity;

	@QueryProjection
	public CartQueryResult(Long id, Long memberId, Long itemId, String itemName, String brandName,
		String categoryName, Integer itemPrice, Integer quantity) {
		this.id = id;
		this.memberId = memberId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
	}
}
