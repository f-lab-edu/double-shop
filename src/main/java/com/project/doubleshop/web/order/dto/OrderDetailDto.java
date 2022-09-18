package com.project.doubleshop.web.order.dto;

import java.util.List;

import com.project.doubleshop.web.item.dto.ItemApiResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderDetailDto {

	private OrderApiResult order;

	private List<ItemApiResult> item;

}
