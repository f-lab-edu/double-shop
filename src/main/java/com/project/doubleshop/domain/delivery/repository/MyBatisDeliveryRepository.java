package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.entity.DeliveryStatus;
import com.project.doubleshop.domain.delivery.mapper.DeliveryMapper;
import com.project.doubleshop.domain.delivery.service.DispatchDriver;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisDeliveryRepository implements DeliveryRepository {

	private final DeliveryMapper mapper;

	@Override
	public boolean save(Delivery delivery) {
		int affectedRowCnt;
		if (delivery.getId() != null) {
			affectedRowCnt = mapper.updateDelivery(delivery);
		} else {
			affectedRowCnt = mapper.insertDelivery(delivery);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public Delivery findById(Long id) {
		return mapper.selectByDeliveryId(id);
	}

	@Override
	public List<Delivery> findAll(Pageable pageable) {
		return mapper.selectAllDelivery(pageable);
	}

	@Override
	public void bulkInsert(List<Delivery> deliveries) {
		/*TODO 2건 이상의 배송 데이터를 insert하는 데이터 액세스 로직 구현하기*/
	}

	@Override
	public List<Delivery> findDeliveriesByDeliveryStatus(DeliveryStatus statusPreparation) {
		return null;
	}

	@Override
	public void bulkUpdateDeliveryStatusByOrderIds(List<Long> deliveryIds, DeliveryStatus statusBeginning) {
		/*TODO 주문 fk를 통해, 배송 상태를 업데이트 하는 데이터 액세스 로직 구현하기*/
	}

	@Override
	public void batchUpdate(List<DispatchDriver> result) {
		/*TODO 전달 받은 result를 활용하여, 배치 업데이트 쿼리를 실행하는 로직 구현하기*/
	}

	@Override
	public int updateStatus(StatusRequest requestDTO) {
		return mapper.updateDeliveryStatus(requestDTO);
	}

	@Override
	public int deleteData(Status status) {
		return mapper.deleteDelivery(status);
	}
}
