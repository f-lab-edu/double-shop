package com.project.doubleshop.domain.category.entity;

import com.project.doubleshop.domain.common.Status;

public enum DepthLevel {
	DEPTH_ONE,
	DEPTH_TWO,
	DEPTH_THREE;

	public static Status of(String value) {
		for (Status status : Status.values()) {
			if (status.name().equalsIgnoreCase(value)) {
				return status;
			}
		}
		return null;
	}
}
