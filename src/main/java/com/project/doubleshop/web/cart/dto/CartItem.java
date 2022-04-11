package com.project.doubleshop.web.cart.dto;

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

	public CartItem(Item source) {
		this.id = source.getId();
		this.name = source.getName();
		this.brandName = source.getBrandName();
		this.categoryName = source.getCategory().getName();
		this.price = source.getPrice();
	}
}
