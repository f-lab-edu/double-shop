package com.project.doubleshop.domain.address.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.project.doubleshop.domain.address.entity.Address;

public interface AddressMapper {
	int insertAddress(Address address);
	List<Address> selectByMemberId(Long memberId);
	Address selectAddressById(Long id);
	int updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> addressIds);
	int updateAddress(Address address);
	int deleteAddress(Integer statusCode);
}
