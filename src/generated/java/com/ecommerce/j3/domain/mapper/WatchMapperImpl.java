package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.WatchDto.WatchApiRequest;
import com.ecommerce.j3.controller.dto.WatchDto.WatchApiResponse;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.domain.entity.Watch.WatchBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-16T23:36:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class WatchMapperImpl extends WatchMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Watch toEntity(WatchApiRequest dto) {
        if ( dto == null ) {
            return null;
        }

        WatchBuilder watch = Watch.builder();

        watch.account( commonMapper.mapIdToAccount( dto.getAccountId() ) );
        watch.product( commonMapper.mapIdToProduct( dto.getProductId() ) );
        watch.watchId( dto.getWatchId() );
        watch.recentWatch( dto.getRecentWatch() );
        watch.watchCount( dto.getWatchCount() );

        return watch.build();
    }

    @Override
    public WatchApiResponse toApiResponse(Watch entity) {
        if ( entity == null ) {
            return null;
        }

        WatchApiResponse watchApiResponse = new WatchApiResponse();

        watchApiResponse.setWatchId( entity.getWatchId() );
        watchApiResponse.setAccount( entity.getAccount() );
        watchApiResponse.setProduct( entity.getProduct() );
        watchApiResponse.setRecentWatch( entity.getRecentWatch() );
        watchApiResponse.setWatchCount( entity.getWatchCount() );

        return watchApiResponse;
    }
}
