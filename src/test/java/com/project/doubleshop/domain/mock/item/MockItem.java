package com.project.doubleshop.domain.mock.item;

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

	public static class Item2 {
		public static final Long ID = 2L;
		public static final String NAME = "상품이름2";
		public static final String DESCRIPTION = "상품설명2";
		public static final String BRAND_NAME = "브랜드 명2";
		public static final Integer PRICE = 1300;
		public static final String PACKAGE_TYPE = "비닐포장2";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "1845265762 - 3121080524";
		public static final String SEARCH_KEYWORD = "가성비 검색어2";
		public static final Integer STOCK = 27;
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
	}

	public static class Item3 {
		public static final Long ID = 3L;
		public static final String NAME = "상품이름3";
		public static final String DESCRIPTION = "상품설명3";
		public static final String BRAND_NAME = "브랜드 명3";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장3";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "6845265762 - 3121080524";
		public static final String SEARCH_KEYWORD = "가성비 검색어3";
		public static final Integer STOCK = 57;
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
	}

	public static class Item4 {
		public static final Long ID = 4L;
		public static final String NAME = "상품이름4";
		public static final String DESCRIPTION = "상품설명4";
		public static final String BRAND_NAME = "브랜드 명4";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장4";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "6845263452 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어4";
		public static final Integer STOCK = 57;
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
	}

	public static class Item5 {
		public static final Long ID = 5L;
		public static final String NAME = "상품이름5";
		public static final String DESCRIPTION = "상품설명5";
		public static final String BRAND_NAME = "브랜드 명5";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장5";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어5";
		public static final Integer STOCK = 57;
		public static final Boolean IS_FRESH_ELIGIBLE = true;
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
			.isFreshEligible(IS_FRESH_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item6 {
		public static final Long ID = 6L;
		public static final String NAME = "상품이름6";
		public static final String DESCRIPTION = "상품설명6";
		public static final String BRAND_NAME = "브랜드 명6";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장6";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어6";
		public static final Integer STOCK = 57;
		public static final Boolean IS_FRESH_ELIGIBLE = true;
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
			.isFreshEligible(IS_FRESH_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item7 {
		public static final Long ID = 7L;
		public static final String NAME = "상품이름7";
		public static final String DESCRIPTION = "상품설명7";
		public static final String BRAND_NAME = "브랜드 명7";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장7";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어7";
		public static final Integer STOCK = 57;
		public static final Boolean IS_FRESH_ELIGIBLE = true;
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
			.isFreshEligible(IS_FRESH_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item8 {
		public static final Long ID = 8L;
		public static final String NAME = "상품이름8";
		public static final String DESCRIPTION = "상품설명8";
		public static final String BRAND_NAME = "브랜드 명8";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장8";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어6";
		public static final Integer STOCK = 57;
		public static final Boolean IS_FRESH_ELIGIBLE = true;
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
			.isFreshEligible(IS_FRESH_ELIGIBLE)
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item9 {
		public static final Long ID = 9L;
		public static final String NAME = "상품이름9";
		public static final String DESCRIPTION = "상품설명9";
		public static final String BRAND_NAME = "브랜드 명9";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장9";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어9";
		public static final Integer STOCK = 57;
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
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item10 {
		public static final Long ID = 10L;
		public static final String NAME = "상품이름10";
		public static final String DESCRIPTION = "상품설명10";
		public static final String BRAND_NAME = "브랜드 명10";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장10";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어10";
		public static final Integer STOCK = 57;
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
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item11 {
		public static final Long ID = 9L;
		public static final String NAME = "상품이름11";
		public static final String DESCRIPTION = "상품설명11";
		public static final String BRAND_NAME = "브랜드 명11";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장11";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어11";
		public static final Integer STOCK = 57;
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
			.categoryId(CATEGORY_ID)
			.build();
	}

	public static class Item12 {
		public static final Long ID = 12L;
		public static final String NAME = "상품이름12";
		public static final String DESCRIPTION = "상품설명12";
		public static final String BRAND_NAME = "브랜드 명12";
		public static final Integer PRICE = 1400;
		public static final String PACKAGE_TYPE = "비닐포장12";
		public static final String ORIGIN = "국내산";
		public static final String MODEL_SERIAL_NO = "2634684552 - 3110802524";
		public static final String SEARCH_KEYWORD = "가성비 검색어12";
		public static final Integer STOCK = 57;
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
			.categoryId(CATEGORY_ID)
			.build();
	}
}