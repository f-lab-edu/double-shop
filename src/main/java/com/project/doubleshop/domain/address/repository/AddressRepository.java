package com.project.doubleshop.domain.address.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.project.doubleshop.domain.address.entity.Address;
import com.project.doubleshop.domain.common.Status;

public interface AddressRepository {
	boolean save(Address address);

	List<Address> findByMemberId(Long memberId);

	Address findById(Long id);

	Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> addressIds);

	Integer deleteAddress(Status status);
}
