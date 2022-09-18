package com.project.doubleshop.cart.repository.querydsl;

import static com.project.doubleshop.cart.entity.QCart.*;
import static com.project.doubleshop.item.entity.QItem.*;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartQueryRepositoryImpl implements CartQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	@Cacheable(value = "cartsForMember", key = "#memberId")
	public List<CartQueryResult> findCartsJoinWithItem(Long memberId) {
		return jpaQueryFactory.select(Projections.constructor(CartQueryResult.class,
			cart.id,
			cart.member.id,
			item.id,
			item.name,
			item.brandName,
			item.category.name,
			item.price,
			cart.quantity
		)).from(cart)
			.join(item).on(cart.item.id.eq(item.id))
			.where(cart.member.id.eq(memberId))
			.orderBy(cart.id.desc())
			.fetchResults()
			.getResults();
	}
}
