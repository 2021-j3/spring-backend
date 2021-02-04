package com.ecommerce.j3.repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

public class WatchRepositoryCustomImpl implements WatchRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Long save(Long watchId, Long accountId, Long productId, LocalDateTime recentWatch, Integer watchCount) {
        saveNow(watchId, accountId, productId, recentWatch, watchCount);
//        BigInteger bi = (BigInteger)em.createQuery("SELECT  last_insert_id()").getSingleResult();
//        return bi.longValue();
        return 1L;
    }

    @Transactional
    protected void saveNow(Long watchId, Long accountId, Long productId, LocalDateTime recentWatch, Integer watchCount) {
        em.createNativeQuery("INSERT INTO shop.watch" +
                " (watch_id, account_id, product_id, recent_watch, watch_count) " +
                "VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, watchId)
                .setParameter(2, accountId)
                .setParameter(3, productId)
                .setParameter(4, recentWatch)
                .setParameter(5, watchCount).executeUpdate();
    }
}
