package com.ecommerce.j3.domain.repository;

import com.ecommerce.j3.J3ApplicationTests;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.domain.repository.AccountRepository;
import com.ecommerce.j3.domain.repository.ProductRepository;
import com.ecommerce.j3.domain.repository.WatchRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
public class WatchRepositoryTest extends J3ApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WatchRepository watchRepository;

    @Transactional
    @Test
    public void create(){
        Watch watch = new Watch();

        watch.setWatchId(2L);
        watch.setAccount(accountRepository.getOne(2L));
        watch.setProduct(productRepository.getOne(2L));
        watch.setRecentWatch(LocalDateTime.now());
        watch.setWatchCount(2);

        Watch newWatch = watchRepository.save(watch);
        Assert.assertNotNull(newWatch);

    }
}
