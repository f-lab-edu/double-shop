package com.project.doubleshop.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.domain.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
