package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByEmail(String email);
    public Optional<Account> findByPhoneNumber(String phoneNumber);
    @Modifying
    @Query("UPDATE Account a set a.lastLogin=current_timestamp WHERE a.email=:email")
    public void updateAccountSetLastLogin(String email);
}
