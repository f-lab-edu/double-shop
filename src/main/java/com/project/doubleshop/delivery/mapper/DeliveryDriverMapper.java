package com.project.doubleshop.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.legacy.DeliveryDriver;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Mapper
public interface DeliveryDriverMapper {
	int insertDeliveryDriver(DeliveryDriver deliveryDriver);
	DeliveryDriver selectByDeliveryDriverId(Long id);
	List<DeliveryDriver> selectAllDeliveryDriver(Pageable pageable);
	int updateDeliveryDriver(DeliveryDriver deliveryDriver);
	int deleteDeliveryDriver(Status status);
	int updateDeliveryDriverStatus(StatusRequest statusRequest);
}
