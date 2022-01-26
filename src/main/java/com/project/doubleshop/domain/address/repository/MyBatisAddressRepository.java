package com.project.doubleshop.domain.address.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.doubleshop.domain.address.entity.Address;
import com.project.doubleshop.domain.address.mapper.AddressMapper;
import com.project.doubleshop.domain.common.Status;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisAddressRepository implements AddressRepository {

	private final AddressMapper mapper;

	@Override
	public boolean save(Address address) {
		int affectedRowCnt = 0;
		if (address.getId() != null) {
			affectedRowCnt = mapper.updateAddress(address);
		} else {
			affectedRowCnt = mapper.insertAddress(address);
		}
		return affectedRowCnt != 0;
	}

	@Override
	public List<Address> findByMemberId(Long memberId) {
		return mapper.selectAddressByMemberId(memberId);
	}

	@Override
	public Address findById(Long id) {
		return mapper.selectAddressById(id);
	}

	@Override
	public Integer updateStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> addressIds) {
		return mapper.updateAddressStatus(statusCode, statusUpdateTime, addressIds);
	}

	@Override
	public Integer deleteAddress(Status status) {
		return mapper.deleteAddress(status.getValue());
	}
}
