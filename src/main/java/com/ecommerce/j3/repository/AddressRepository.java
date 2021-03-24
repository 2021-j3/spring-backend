package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value="SELECT * FROM address AS a WHERE a.account_id=:accountId", nativeQuery = true)
    List<Address> findByAccountId(@Param("accountId") Long accountId);

    @Query(value="SELECT address FROM default_address AS da INNER JOIN address WHERE da.account_id=:accountId", nativeQuery = true)
    Address findDeaultAddressByAccountId(@Param("accountId") Long accountId);
}
