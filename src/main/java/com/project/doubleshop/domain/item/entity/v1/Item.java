package com.project.doubleshop.domain.item.entity.v1;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public abstract class Item {
	// 상품 pk
	private Long id;

	// 상품 이름
	private String name;

	// 상품 가격
	private Integer price;
}
