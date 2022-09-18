package com.project.doubleshop.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.doubleshop.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
