package com.project.doubleshop.web.common;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class StatusRequest {
	Long id;
	Status status;

	public StatusRequest(Status status) {
		this.status = status;
	}
}
