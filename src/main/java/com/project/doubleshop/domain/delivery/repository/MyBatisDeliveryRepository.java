package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.domain.delivery.mapper.DeliveryMapper;
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
}
