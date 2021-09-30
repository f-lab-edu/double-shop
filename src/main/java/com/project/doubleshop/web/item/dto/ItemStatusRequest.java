package com.project.doubleshop.web.item.dto;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ItemStatusRequest {
	Long id;
	Status status;

	public ItemStatusRequest(Status status) {
		this.status = status;
	}
}
