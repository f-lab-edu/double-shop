package com.project.doubleshop.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.Delivery;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Mapper
public interface DeliveryMapper {
	int insertDelivery(Delivery delivery);
	Delivery selectByDeliveryId(Long id);
	List<Delivery> selectAllDelivery(Pageable pageable);
	int updateDelivery(Delivery delivery);
	int deleteDelivery(Status status);
	int updateDeliveryStatus(StatusRequest statusRequest);
}
