package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.Watch;

import java.util.Optional;

public interface WatchRepositoryInterface {
    Optional<Watch> findOneByParent(Watch review);
}