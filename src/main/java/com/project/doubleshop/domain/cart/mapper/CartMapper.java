package com.project.doubleshop.domain.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.cart.entity.Cart;

@Mapper
public interface CartMapper {
	Cart selectCartById(Long cartId, Long memberId);
	List<Cart> selectCartInIds(List<Long> cartIds, Long memberId);
	List<Cart> selectCartByMemberId(Long memberId);
	int insertCart(Cart cart);
	int deleteCart(Long memberId, List<Long> cartIds);
	int updateQuantity(Integer quantity, Long id, Long memberId);
}
