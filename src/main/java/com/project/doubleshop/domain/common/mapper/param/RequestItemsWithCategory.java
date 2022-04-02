package com.project.doubleshop.domain.common.mapper.param;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RequestItemsWithCategory {

	private Long categoryId;

	private Long page;

	private int size;

}
