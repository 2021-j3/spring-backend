package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long>, WatchRepositoryInterface, WatchRepositoryCustom{
    @Override
    List<Watch> findByAccount(Account account);
}
