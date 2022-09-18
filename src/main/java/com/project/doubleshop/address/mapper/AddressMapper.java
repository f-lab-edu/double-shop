package com.project.doubleshop.address.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.doubleshop.address.entity.Address;

@Mapper
public interface AddressMapper {
	int insertAddress(Address address);
	List<Address> selectAddressByMemberId(Long memberId);
	Address selectAddressById(Long id);
	int updateAddressStatus(Integer statusCode, LocalDateTime statusUpdateTime, List<Long> addressIds);
	int updateAddress(Address address);
	int deleteAddress(Integer statusCode);
}
