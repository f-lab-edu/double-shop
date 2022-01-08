package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.DeliveryDriver;
import com.project.doubleshop.domain.delivery.mapper.DeliveryDriverMapper;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisDeliveryDriverRepository implements DeliveryDriverRepository {

	private final DeliveryDriverMapper mapper;

	@Override
	public boolean save(DeliveryDriver deliveryDriver) {
		int affectedRowCnt;
		if (deliveryDriver.getId() != null) {
			affectedRowCnt = mapper.updateDeliveryDriver(deliveryDriver);
		} else {
			affectedRowCnt = mapper.insertDeliveryDriver(deliveryDriver);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public DeliveryDriver findById(Long id) {
		return mapper.selectByDeliveryDriverId(id);
	}

	@Override
	public List<DeliveryDriver> findAll(Pageable pageable) {
		return mapper.selectAllDeliveryDriver(pageable);
	}

	@Override
	public List<DeliveryDriver> findValidDrivers() {
		/*TODO Status가 ACTIVATED인 모든 운전수들을 조회하는 데이터 액세스 로직 구현하기*/
		return null;
	}

	@Override
	public int updateStatus(StatusRequest requestDTO) {
		return mapper.updateDeliveryDriverStatus(requestDTO);
	}

	@Override
	public int deleteData(Status status) {
		return mapper.deleteDeliveryDriver(status);
	}
}
