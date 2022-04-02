package com.project.doubleshop.domain.delivery.entity.enumuration.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.project.doubleshop.domain.delivery.entity.enumuration.DeliveryStatus;

@Converter(autoApply = true)
public class DeliveryStatusConverter implements AttributeConverter<DeliveryStatus, Integer> {
	@Override
	public Integer convertToDatabaseColumn(DeliveryStatus attribute) {
		if (attribute == null) {
			return DeliveryStatus.PRODUCT_PREPARATION.getValue();
		}
		return attribute.getValue();
	}

	@Override
	public DeliveryStatus convertToEntityAttribute(Integer dbData) {
		return DeliveryStatus.of(dbData);
	}
}
