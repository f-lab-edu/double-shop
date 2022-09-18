package com.project.doubleshop.delivery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.delivery.entity.DeliveryPolicy;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;

@Mapper
public interface DeliveryPolicyMapper {
	int insertDeliveryPolicy(DeliveryPolicy deliveryPolicy);
	DeliveryPolicy selectByDeliveryPolicy(Long id);
	List<DeliveryPolicy> selectAllDeliveryPolicy(Pageable pageable);
	int updateDeliveryPolicy(DeliveryPolicy deliveryPolicy);
	int deleteDeliveryPolicy(Status status);
	int updateDeliveryPolicyStatus(StatusRequest statusRequest);
}
