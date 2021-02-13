package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Watch;

import java.util.List;

public interface WatchRepositoryInterface {
    List<Watch> findByAccount(Account account);
}