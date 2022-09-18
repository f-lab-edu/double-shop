package com.project.doubleshop.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status status) {
		if (status == null) {
			return Status.ACTIVATED.getValue();
		}
		return status.getValue();
	}

	@Override
	public Status convertToEntityAttribute(Integer code) {
		return Status.of(code);
	}
}
