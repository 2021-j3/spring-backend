package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.WatchDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.domain.mapper.WatchMapper;
import com.ecommerce.j3.repository.WatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WatchService {
    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;

    public WatchDto.WatchApiResponse save(WatchDto.WatchApiRequest request){
        Watch watch = watchMapper.toEntity(request);
        watchRepository.save(watch);
        return watchMapper.toDto(watch);
    }
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
