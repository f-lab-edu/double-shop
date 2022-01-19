package com.project.doubleshop.domain.address;

import java.time.LocalDateTime;

import com.project.doubleshop.domain.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Address {
	private Long id;

	private String city;

	private String zipcode;

	private String detail;

	private Status status;

	private LocalDateTime statusUpdateTime;

	private Long memberId;
}
