package com.ecommerce.j3.repository;

import java.time.LocalDateTime;

public interface WatchRepositoryCustom {
    Long save(Long watchId, Long accountId, Long productId, LocalDateTime recentWatch, Integer watchCount);
}
