package com.project.doubleshop.category.entity;

import com.project.doubleshop.common.Status;

public enum CategoryType {
	// 카테고리의 계열을 알려주는 역할을 하는 enum
	// 대 카테고리의 이름과 동일한 경우도 있음

	CLOTH, 				// 패션 잡화
	BEAUTY, 			// 뷰티
	BABY, 				// 출산/유아동
	FOOD, 				// 식품
	KITCHEN, 			// 주방용품
	HOUSE_GOODS,		// 생활용품
	INTERIOR, 			// 홈 인테리어
	ELECTRONICS,		// 가전 디지털
	SPORTS,				// 스포츠, 레저
	CAR_ACCESSORIES, 	// 자동차 용품
	BOOK, 				// 도서
	TOY, 				// 완구, 취미
	STATIONERY, 		// 문구
	PET, 				// 반려동물
	HEALTH,				// 헬스, 건강식품
	NOT_ASSIGNED;

	public static Status of(String value) {
		for (Status status : Status.values()) {
			if (status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		return null;
	}
}
