package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Account;
import com.ecommerce.j3.domain.Watch;

import java.util.List;
import java.util.Optional;

public interface WatchRepositoryInterface {
    List<Watch> findByAccount(Account account);
}