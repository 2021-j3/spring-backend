package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchService {
    private final WatchRepository watchRepository;

    @Autowired
    public WatchService(WatchRepository watchRepository){this.watchRepository = watchRepository;}

    public Watch save(Watch watch){
        watchRepository.save(watch);
        return watch;
    }

    public Watch update(Watch watch){
        watchRepository.save(watch);
        return watch;
    }

    public int increase(Watch watch){
//        watch.setWatchCount(watch.getWatchCount() + 1);
        update(watch);
        return watch.getWatchCount();
    }

    public Optional<Watch> findOne(Long watchId){
        return watchRepository.findById(watchId);
    }

    public List<Watch> findByAccount(Account account){return watchRepository.findByAccount(account);}

    public List<Watch> findAll(){
        return watchRepository.findAll();
    }

    public void remove(Watch watch){
        watchRepository.delete(watch);
    }
}
