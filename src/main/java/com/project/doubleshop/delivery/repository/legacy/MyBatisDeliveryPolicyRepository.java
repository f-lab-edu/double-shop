package com.project.doubleshop.delivery.repository.legacy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.delivery.mapper.DeliveryPolicyMapper;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisDeliveryPolicyRepository implements DeliveryPolicyRepository {

	private final DeliveryPolicyMapper mapper;

	@Override
	public boolean save(DeliveryPolicy deliveryPolicy) {
		int affectedRowCnt;
		if (deliveryPolicy.getId() != null) {
			affectedRowCnt = mapper.updateDeliveryPolicy(deliveryPolicy);
		} else {
			affectedRowCnt = mapper.insertDeliveryPolicy(deliveryPolicy);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public DeliveryPolicy findById(Long id) {
		return mapper.selectByDeliveryPolicy(id);
	}

	@Override
	public List<DeliveryPolicy> findAll(Pageable pageable) {
		return mapper.selectAllDeliveryPolicy(pageable);
	}

	@Override
	public int updateStatus(StatusRequest requestDTO) {
		return mapper.updateDeliveryPolicyStatus(requestDTO);
	}

	@Override
	public int deleteData(Status status) {
		return mapper.deleteDeliveryPolicy(status);
	}
}
