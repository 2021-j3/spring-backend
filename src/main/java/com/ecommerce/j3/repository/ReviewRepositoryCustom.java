package com.ecommerce.j3.repository;

import java.time.LocalDateTime;

public interface ReviewRepositoryCustom {
    Long save(Long reviewId, Long parentId, Long orderItemId, Long accountId, Integer rate, String title, String content, LocalDateTime createdAt);
}
