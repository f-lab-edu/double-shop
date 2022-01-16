package com.project.doubleshop.domain.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.cart.entity.Cart;

@Mapper
public interface CartMapper {
	List<Cart> selectCartByMemberId(Long memberId);
	int insertCart(Cart cart);
	int deleteCart(List<Long> cartIds);
}
