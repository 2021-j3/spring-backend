package com.ecommerce.j3.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Long reviewId, Long parentId, Long orderItemId, Long accountId, Integer rate, String title, String content, LocalDateTime createdAt) {
        BigInteger bi = (BigInteger) em.createNativeQuery("INSERT INTO shop.review" +
                " (review_id, parent_id, order_item_id, account_id, rate, title, content, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ? ?)")
                .setParameter(1, reviewId)
                .setParameter(2, parentId)
                .setParameter(3, orderItemId)
                .setParameter(4, accountId)
                .setParameter(5, rate)
                .setParameter(6, title)
                .setParameter(7, content)
                .setParameter(8, createdAt)
                .getSingleResult();
        return bi.longValue();
    }
}
