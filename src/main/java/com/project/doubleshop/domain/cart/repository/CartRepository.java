package com.project.doubleshop.domain.cart.repository;

import java.util.List;

import com.project.doubleshop.domain.cart.entity.Cart;

public interface CartRepository {
	int saveCarts(List<Cart> carts);
	int deleteCarts(List<Long> itemIds);
}
