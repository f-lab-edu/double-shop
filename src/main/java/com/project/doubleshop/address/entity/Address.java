package com.project.doubleshop.address.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.project.doubleshop.common.Status;
import com.project.doubleshop.common.StatusConverter;
import com.project.doubleshop.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String city;

	private String zipcode;

	private String detail;

	@Convert(converter = StatusConverter.class)
	private Status status;

	@Column(insertable = false, updatable = false,
		columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime statusUpdateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
