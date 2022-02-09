package com.project.doubleshop.domain.delivery.service;

import static com.project.doubleshop.domain.delivery.service.MockDelivery.Delivery1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.repository.DeliveryRepository;
import com.project.doubleshop.domain.exception.NotFoundException;
import com.project.doubleshop.web.config.support.Pageable;
import com.project.doubleshop.web.item.exception.DataNotFoundException;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

	@Mock
	DeliveryRepository deliveryRepository;

	@Mock
	Pageable pageable;

	@InjectMocks
	DeliveryService deliveryService;

	@Test
	@DisplayName("배송등록 성공")
	void saveDelivery() {
		given(deliveryRepository.save(DELIVERY_FORM)).willReturn(true);

		boolean result = deliveryService.saveDelivery(DELIVERY_FORM);

		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("배송 단건 조회 성공")
	void findOneDelivery() {
		given(deliveryRepository.findById(ID)).willReturn(DELIVERY_1);

		Delivery result = deliveryService.findById(ID);

		assertThat(result.getId()).isEqualTo(ID);
	}

	@Test
	@DisplayName("배송 조회 실패")
	void findOneDeliveryFail() {
		given(deliveryRepository.findById(ID)).willReturn(null);

		assertThrows(NotFoundException.class, () -> deliveryService.findById(ID));
	}

	@Test
	@DisplayName("배송 수정 성공")
	void updateDelivery() {
		given(deliveryRepository.findById(ID)).willReturn(DELIVERY_1);
		given(deliveryRepository.save(DELIVERY_1)).willReturn(true);

		deliveryService.saveDelivery(DELIVERY_1, ID);

		then(deliveryRepository).should(times(1)).save(DELIVERY_1);
	}

	@Test
	@DisplayName("배송 목록 조회 성공")
	void findAllDeliveries() {
		given(deliveryRepository.findAll(pageable)).willReturn(anyList());

		deliveryService.findDeliveries(pageable);

		then(deliveryRepository).should(times(1)).findAll(pageable);
	}
}