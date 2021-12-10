package com.project.doubleshop.domain.delivery.repository;

import java.util.List;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.web.config.support.Pageable;

public interface DeliveryRepository {

	boolean save(Delivery delivery);

	Delivery findById(Long id);

	List<Delivery> findAll(Pageable pageable);
}
