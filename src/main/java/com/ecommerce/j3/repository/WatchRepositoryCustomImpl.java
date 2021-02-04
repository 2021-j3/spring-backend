package com.ecommerce.j3.repository;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

// mapper로 가져오면 여러 객체를 손봐야 하므로
// id만 가지고 처리할 수 있도록 커스텀 로직 클래스를 도입해보았습니다

// 커스텀 메소드를 구현한 클래스입니다
// watch repository 를 통해 접근가능합니다
public class WatchRepositoryCustomImpl implements WatchRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    // insert, update, delete 는 트랜잭셔널 하게 수행해야 합니다
    // 아니면 @Modifying 을 대신 사용해도 됩니다
    @Transactional
    @Override
    public Long save(Long watchId, Long accountId, Long productId, LocalDateTime recentWatch, Integer watchCount) {
        saveNow(watchId, accountId, productId, recentWatch, watchCount);
//        BigInteger bi = (BigInteger)em.createQuery("SELECT  last_insert_id()").getSingleResult();
//        return bi.longValue();
        // 아이디를 받고 싶은데, 정 안되면
        // Select * 과 .resultSet 으로 Watch 객체를 리턴하는 것도 고려해볼 필요가 있습니다
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
