package com.project.doubleshop.delivery.entity.enumuration.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.project.doubleshop.delivery.entity.enumuration.FeePolicy;

@Converter(autoApply = true)
public class FeePolicyConverter implements AttributeConverter<FeePolicy, Integer> {
	@Override
	public Integer convertToDatabaseColumn(FeePolicy feePolicy) {
		if (feePolicy == null) {
			return FeePolicy.FREE.getValue();
		}
		return feePolicy.getValue();
	}

	@Override
	public FeePolicy convertToEntityAttribute(Integer code) {
		return FeePolicy.of(code);
	}
}
