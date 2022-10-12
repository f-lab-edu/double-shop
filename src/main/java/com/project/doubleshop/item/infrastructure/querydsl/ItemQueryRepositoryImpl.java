package com.project.doubleshop.item.infrastructure.querydsl;

import static com.project.doubleshop.item.entity.QItem.*;
import static com.querydsl.sql.SQLExpressions.*;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import com.project.doubleshop.common.Status;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository {

	private final SQLQueryFactory sqlQueryFactory;

	@Override
	@Cacheable(value = "todayItems", key = "#pageable.getPageNumber()")
	public List<ItemQueryApiResult> findItemsPerCategory(Pageable pageable) {
		if (pageable.getOffset() == 0) {
			throw new IllegalArgumentException("Parameter 'offset' must be at least 1.");
		}

		int max = pageable.getPageSize() * pageable.getPageNumber();
		int min = max - pageable.getPageSize() + 1;

		return sqlQueryFactory.select(Projections.constructor(ItemQueryApiResult.class,
				item.id, item.name, item.description, Expressions.stringPath("brand_name"),
				item.price, item.rating, Expressions.stringPath("search_keyword"),
			Expressions.numberPath(Long.class, "category_id")
			)
		).from(
			select(
				item.id, item.name, item.description, Expressions.stringPath("brand_name"),
				item.price, item.rating, Expressions.stringPath("search_keyword"), Expressions.numberPath(Integer.class, "status"),
				Expressions.numberPath(Long.class, "category_id"),
				rowNumberOverPartitionBy(Expressions.numberPath(Long.class, "category_id")))
			.from(item)
				.where(
					Expressions.numberPath(Integer.class, "status")
					.eq(Status.ACTIVATED.getValue()))
				.as(item)
		).where(Expressions.numberPath(Long.class, "rowNumber")
				.between(min, max)
		)
			.fetchResults().getResults();
	}

	private SimpleExpression<Long> rowNumberOverPartitionBy(Expression<?> partitionBy) {
		return SQLExpressions.rowNumber()
			.over()
			.partitionBy(partitionBy)
			.orderBy(item.id.desc()).as("rowNumber");
	}
}
