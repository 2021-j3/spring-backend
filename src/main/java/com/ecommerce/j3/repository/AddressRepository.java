package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
