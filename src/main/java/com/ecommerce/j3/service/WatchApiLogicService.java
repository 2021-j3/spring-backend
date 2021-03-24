package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.WatchDto.WatchApiRequest;
import com.ecommerce.j3.controller.dto.WatchDto.WatchApiResponse;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.domain.mapper.WatchMapper;
import com.ecommerce.j3.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WatchApiLogicService {
    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;

    public WatchApiResponse saveWatch(WatchApiRequest request) {
        Watch watch = watchMapper.toEntity(request);
        Long id = watchRepository.save(
                request.getWatchId(),
                request.getAccountId(),
                request.getWatchId(),
                request.getRecentWatch(),
                request.getWatchCount());
        return watchMapper.toApiResponse(watch);
    }

//    public WatchApiResponse saveWatch(WatchApiRequest request) {
//        Watch watch = watchMapper.toEntity(request);
//        watchRepository.save(watch);
//        return watchMapper.toApiResponse(watch);
//    }

    public WatchApiResponse updateWatch(WatchApiRequest request) {
        Watch watchFromDB = findById(request.getWatchId());
        watchMapper.updateFromDto(watchFromDB, request);
        watchRepository.save(watchFromDB);
        return watchMapper.toApiResponse(watchFromDB);
    }

    public WatchApiResponse findWatch(Long watchId) {
        Watch watchFromDB = findById(watchId);
        return watchMapper.toApiResponse(watchFromDB);
    }

    public void removeWatch(Long id) {
        watchRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Watch findById(Long id) {
        return watchRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}

