package com.project.doubleshop.domain.item.service;

import com.project.doubleshop.domain.item.entity.Item;
import com.project.doubleshop.web.item.dto.ItemForm;

public class MockItem {
	public static class Item1 {
		public static final Long ID = 1L;
		public static final String NAME = "상품이름";
		public static final String DESCRIPTION = "상품설명";
		public static final String BRAND_NAME = "브랜드 명";
		public static final Integer PRICE = 1300;
		public static final String PACKAGE_TYPE = "비닐포장";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "1835265762 - 3121080524";
		public static final String SEARCH_KEYWORD = "가성비 검색어";
		public static final Integer STOCK = 365;
		public static final Boolean IS_ONEDAY_ELIGIBLE = true;
		public static final Long CATEGORY_ID = 1L;

		public static final Item ITEM = Item.builder()
			.id(ID)
			.name(NAME)
			.description(DESCRIPTION)
			.brandName(BRAND_NAME)
			.price(PRICE)
			.packageType(PACKAGE_TYPE)
			.origin(ORIGIN)
			.modelSerialNo(MODEL_SERIAL_NO)
			.searchKeyword(SEARCH_KEYWORD)
			.stock(STOCK)
			.isOnedayEligible(IS_ONEDAY_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();

		public static final Item NEW_ITEM = Item.builder()
			.name(NAME)
			.description(DESCRIPTION)
			.brandName(BRAND_NAME)
			.price(PRICE)
			.packageType(PACKAGE_TYPE)
			.origin(ORIGIN)
			.modelSerialNo(MODEL_SERIAL_NO)
			.searchKeyword(SEARCH_KEYWORD)
			.stock(STOCK)
			.isOnedayEligible(IS_ONEDAY_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();

		public static final ItemForm ITEM_FORM_NEW = new ItemForm(NEW_ITEM);

		public static final ItemForm ITEM_FORM_UPDATE = new ItemForm(ITEM);
	}
}
