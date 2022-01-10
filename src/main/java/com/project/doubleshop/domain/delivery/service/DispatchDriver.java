package com.project.doubleshop.domain.delivery.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DispatchDriver {
	private Long deliveryId;
	private Long deliveryDriverId;
}
