package com.project.doubleshop.domain.address.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.member.entity.Member;

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

	private Status status;

	private LocalDateTime statusUpdateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
