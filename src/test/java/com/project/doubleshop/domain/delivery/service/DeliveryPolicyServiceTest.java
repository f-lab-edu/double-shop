package com.project.doubleshop.domain.delivery.service;

import static com.project.doubleshop.domain.delivery.service.MockDelivery.DeliveryPolicy1.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.project.doubleshop.domain.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.domain.delivery.repository.DeliveryPolicyRepository;
import com.project.doubleshop.domain.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class DeliveryPolicyServiceTest {

	@Mock
	DeliveryPolicyRepository deliveryPolicyRepository;

	@InjectMocks
	DeliveryPolicyService deliveryPolicyService;

	@Test
	@DisplayName("배송정책 등록 성공")
	void saveDeliveryPolicy() {
		given(deliveryPolicyRepository.save(DELIVERY_POLICY_FORM)).willReturn(DELIVERY_POLICY_1);

		DeliveryPolicy result = deliveryPolicyService.save(DELIVERY_POLICY_FORM);

		assertThat(result).isNotNull();
	}

	@Test
	@DisplayName("배송정책 단건 조회 성공")
	void findOneDeliveryPolicy() {
		given(deliveryPolicyRepository.findById(ID)).willReturn(Optional.of(DELIVERY_POLICY_1));

		DeliveryPolicy result = deliveryPolicyService.findById(ID);

		assertThat(result.getId()).isEqualTo(ID);
	}

	@Test
	@DisplayName("배송정책 조회 실패")
	void findOneDeliveryPolicyFail() {
		given(deliveryPolicyRepository.findById(ID)).willReturn(Optional.ofNullable(null));

		assertThrows(NotFoundException.class, () -> deliveryPolicyService.findById(ID));
	}

	@Test
	@DisplayName("배송정책 수정 성공")
	void updateDeliveryPolicy() {
		given(deliveryPolicyRepository.findById(ID)).willReturn(Optional.of(DELIVERY_POLICY_1));

		deliveryPolicyService.updateDeliveryPolicy(ID, DELIVERY_POLICY_1);

		then(deliveryPolicyRepository).should(times(1)).save(DELIVERY_POLICY_1);
	}
}