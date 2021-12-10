package com.project.doubleshop.domain.delivery.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.delivery.entity.Delivery;

@Mapper
public interface DeliveryMapper {
	int insertDelivery(Delivery delivery);
	Delivery selectByDeliveryId(Long id);
	int updateDelivery(Delivery delivery);
}
