package com.project.doubleshop.domain.delivery.entity.enumuration.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.project.doubleshop.domain.delivery.entity.enumuration.FeeMethod;

@Converter(autoApply = true)
public class FeeMethodConverter implements AttributeConverter<FeeMethod, Integer> {
	@Override
	public Integer convertToDatabaseColumn(FeeMethod attribute) {
		if (attribute == null) {
			return FeeMethod.PREPAID.getValue();
		}
		return attribute.getValue();
	}

	@Override
	public FeeMethod convertToEntityAttribute(Integer dbData) {
		return FeeMethod.of(dbData);
	}
}
