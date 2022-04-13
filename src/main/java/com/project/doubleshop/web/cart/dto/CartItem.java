package com.project.doubleshop.web.cart.dto;

import com.project.doubleshop.domain.cart.repository.querydsl.CartQueryResult;
import com.project.doubleshop.domain.item.entity.Item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CartItem {
	private Long id;
	private String name;
	private String brandName;
	private String categoryName;
	private Integer price;

	public CartItem(CartQueryResult source) {
		this.id = source.getId();
		this.name = source.getItemName();
		this.brandName = source.getBrandName();
		this.categoryName = source.getCategoryName();
		this.price = source.getItemPrice();
	}

	public CartItem(Item source) {
		this.id = source.getId();
		this.name = source.getName();
		this.brandName = source.getBrandName();
		this.categoryName = source.getCategory().getName();
		this.price = source.getPrice();
	}
}
