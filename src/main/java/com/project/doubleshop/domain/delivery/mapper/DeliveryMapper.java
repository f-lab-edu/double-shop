package com.project.doubleshop.domain.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.domain.delivery.entity.Delivery;
import com.project.doubleshop.web.config.support.Pageable;

@Mapper
public interface DeliveryMapper {
	int insertDelivery(Delivery delivery);
	Delivery selectByDeliveryId(Long id);
	List<Delivery> selectAllDelivery(Pageable pageable);
	int updateDelivery(Delivery delivery);
}
