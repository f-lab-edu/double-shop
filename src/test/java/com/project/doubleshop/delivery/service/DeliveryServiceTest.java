package com.project.doubleshop.delivery.service;

import static com.project.doubleshop.delivery.service.MockDelivery.Delivery1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.doubleshop.delivery.entity.Delivery;
import com.project.doubleshop.delivery.repository.DeliveryRepository;
import com.project.doubleshop.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

	@Mock
	DeliveryRepository deliveryRepository;

	@InjectMocks
	DeliveryService deliveryService;

	@Test
	@DisplayName("배송등록 성공")
	void saveDelivery() {
		given(deliveryRepository.save(DELIVERY_FORM)).willReturn(DELIVERY_1);

		Delivery result = deliveryService.save(DELIVERY_FORM);

		assertThat(result).isNotNull();
	}

	@Test
	@DisplayName("배송 단건 조회 성공")
	void findOneDelivery() {
		given(deliveryRepository.findById(ID)).willReturn(Optional.of(DELIVERY_1));

		Delivery result = deliveryService.findById(ID);

		assertThat(result.getId()).isEqualTo(ID);
	}

	@Test
	@DisplayName("배송 조회 실패")
	void findOneDeliveryFail() {
		given(deliveryRepository.findById(ID)).willReturn(Optional.ofNullable(null));

		assertThrows(NotFoundException.class, () -> deliveryService.findById(ID));
	}

	@Test
	@DisplayName("배송 수정 성공")
	void updateDelivery() {
		given(deliveryRepository.findById(ID)).willReturn(Optional.of(DELIVERY_1));
		given(deliveryRepository.save(DELIVERY_1)).willReturn(DELIVERY_1);
		//
		deliveryService.updateDelivery(DELIVERY_1, ID);
		//
		then(deliveryRepository).should(times(1)).save(DELIVERY_1);
	}
}