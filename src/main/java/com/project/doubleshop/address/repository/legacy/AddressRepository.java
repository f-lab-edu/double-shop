package com.project.doubleshop.address.repository.legacy;

import java.time.LocalDateTime;
import java.util.List;

import com.project.doubleshop.address.entity.Address;
import com.project.doubleshop.common.Status;

public interface AddressRepository {
	boolean save(Address address);

	List<Address> findByMemberId(Long memberId);

	Address findById(Long id);

	Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> addressIds);

	Integer deleteAddress(Status status);
}
