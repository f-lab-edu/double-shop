package com.project.doubleshop.web.cart.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartForm {
	private Long id;

	private Long itemId;

	private Integer quantity;
}
