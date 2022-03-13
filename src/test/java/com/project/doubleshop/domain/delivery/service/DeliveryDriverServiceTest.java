package com.project.doubleshop.domain.delivery.service;

import static com.project.doubleshop.domain.delivery.service.MockDelivery.DeliveryDriver1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.domain.delivery.repository.legacy.DeliveryDriverRepository;
import com.project.doubleshop.domain.delivery.service.legacy.DeliveryDriverService;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.web.config.support.Pageable;

@ExtendWith(MockitoExtension.class)
class DeliveryDriverServiceTest {

	@Mock
	DeliveryDriverRepository deliveryDriverRepository;

	@Mock
	Pageable pageable;

	@InjectMocks
	DeliveryDriverService deliveryDriverService;

	@Test
	@DisplayName("배송기사 등록 성공")
	void saveDeliveryDriver() {
		given(deliveryDriverRepository.save(DELIVERY_DRIVER_FORM)).willReturn(true);

		boolean result = deliveryDriverService.saveDeliveryDriver(DELIVERY_DRIVER_FORM);

		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("배송기사 단건 조회 성공")
	void findOneDeliveryPolicy() {
		given(deliveryDriverRepository.findById(ID)).willReturn(DELIVERY_DRIVER_1);

		DeliveryDriver deliveryDriver = deliveryDriverService.findById(ID);

		assertThat(deliveryDriver.getId()).isEqualTo(ID);
	}

	@Test
	@DisplayName("배송기사 조회 실패")
	void findOneDeliveryPolicyFail() {
		given(deliveryDriverRepository.findById(ID)).willReturn(null);

		assertThrows(NotFoundException.class, () -> deliveryDriverService.findById(ID));
	}

	@Test
	@DisplayName("배송기사 수정 성공")
	void updateDeliveryPolicy() {
		given(deliveryDriverRepository.findById(ID)).willReturn(DELIVERY_DRIVER_1);
		given(deliveryDriverRepository.save(DELIVERY_DRIVER_1)).willReturn(true);

		deliveryDriverService.saveDeliveryDriver(DELIVERY_DRIVER_1, ID);

		then(deliveryDriverRepository).should(times(1)).save(DELIVERY_DRIVER_1);
	}

	@Test
	@DisplayName("배송기사 목록 조회 성공")
	void findAllDeliveryPolicies() {
		given(deliveryDriverRepository.findAll(pageable)).willReturn(anyList());

		deliveryDriverService.findDeliverDrivers(pageable);

		then(deliveryDriverRepository).should(times(1)).findAll(pageable);
	}
}