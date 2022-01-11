package com.project.doubleshop.domain.delivery.service;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.domain.delivery.entity.FeeMethod;
import com.project.doubleshop.domain.delivery.entity.FeePolicy;

public class MockDelivery {
	public static class Delivery1 {
		public static final Long ID = 1L;
		public static final String WAYBILL_NUMBER = "110232-123223-9909";
		public static final String MEMO = "배송도착 시 현관문 앞에 놓아주세요.";
		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2021, 12, 13, 9, 27, 1);
		public static final LocalDateTime UPDATE_TIME = null;
		public static final DeliveryStatus DELIVERY_STATUS = DeliveryStatus.PRODUCT_PREPARATION;
		public static final Status STATUS = Status.ACTIVATED;
		public static final LocalDateTime STATUS_UPDATE_TIME = CREATE_TIME;
		public static final Long DELIVERY_POLICY_ID = DeliveryPolicy1.ID;
		public static final Long DELIVERY_DRIVER_ID = DeliveryDriver1.ID;

		public static final Delivery DELIVERY_1 = Delivery.builder()
			.id(ID)
			.waybillNumber(WAYBILL_NUMBER)
			.memo(MEMO)
			.createTime(CREATE_TIME)
			.updateTime(UPDATE_TIME)
			.deliveryStatus(DELIVERY_STATUS)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.deliveryPolicyId(DELIVERY_POLICY_ID)
			.deliveryDriverId(DELIVERY_DRIVER_ID)
			.build();

		public static final Delivery DELIVERY_FORM = Delivery.builder()
			.waybillNumber(WAYBILL_NUMBER)
			.memo(MEMO)
			.createTime(CREATE_TIME)
			.deliveryStatus(DELIVERY_STATUS)
			.status(STATUS)
			.build();
	}

	public static class DeliveryPolicy1 {
		public static final Long ID = 1L;
		public static final String NAME = "기본정책";
		public static final String COMPANY = "배송사이름";
		public static final FeePolicy FEE_POLICY = FeePolicy.FREE;
		public static final FeeMethod FEE_METHOD = FeeMethod.PREPAID;
		public static final int FEE_PRICE = 0;
		public static final int ISLAND_MOUNTAINOUS_FEE = 2700;
		public static final Status STATUS = Status.ACTIVATED;
		public static final LocalDateTime STATUS_UPDATE_TIME = LocalDateTime.of(2021, 11, 13, 9, 27, 1);

		public static final DeliveryPolicy DELIVERY_POLICY_1 = DeliveryPolicy.builder()
			.id(ID)
			.name(NAME)
			.company(COMPANY)
			.feePolicy(FEE_POLICY)
			.feeMethod(FEE_METHOD)
			.feePrice(FEE_PRICE)
			.islandMountainousFee(ISLAND_MOUNTAINOUS_FEE)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();

		public static final DeliveryPolicy DELIVERY_POLICY_FORM = DeliveryPolicy.builder()
			.name(NAME)
			.company(COMPANY)
			.feePolicy(FEE_POLICY)
			.feeMethod(FEE_METHOD)
			.feePrice(FEE_PRICE)
			.islandMountainousFee(ISLAND_MOUNTAINOUS_FEE)
			.status(STATUS)
			.build();
	}

	public static class DeliveryDriver1 {
		public static final Long ID = 1L;
		public static final String NAME = "김기사";
		public static final String PHONE = "010-1234-5678";
		public static final Integer CAPACITY = 2500;
		public static final LocalDateTime CREATE_TIME = LocalDateTime.of(2021, 10, 13, 9, 27, 1);
		public static final LocalDateTime UPDATE_TIME = null;
		public static final Status STATUS = Status.ACTIVATED;
		public static final LocalDateTime STATUS_UPDATE_TIME = CREATE_TIME;

		public static final DeliveryDriver DELIVERY_DRIVER_1 = DeliveryDriver.builder()
			.id(ID)
			.name(NAME)
			.phone(PHONE)
			.capacity(CAPACITY)
			.createTime(CREATE_TIME)
			.updateTime(UPDATE_TIME)
			.status(STATUS)
			.statusUpdateTime(STATUS_UPDATE_TIME)
			.build();

		public static final DeliveryDriver DELIVERY_DRIVER_FORM = DeliveryDriver.builder()
			.name(NAME)
			.phone(PHONE)
			.capacity(CAPACITY)
			.createTime(CREATE_TIME)
			.status(STATUS)
			.build();
	}
}
