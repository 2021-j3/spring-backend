package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.WatchDto.WatchApiRequest;
import com.ecommerce.j3.controller.dto.WatchDto.WatchApiResponse;
import com.ecommerce.j3.domain.entity.Watch;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class WatchMapper implements DefaultMapper<Watch, WatchApiRequest, WatchApiResponse> {

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "productId", target = "product")
    @Override
    public abstract Watch toEntity(WatchApiRequest dto);

    @Override
    public abstract WatchApiResponse toApiResponse(Watch entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public Watch updateFromDto(@MappingTarget Watch entity, WatchApiRequest dto){
        if (dto == null) return entity;
        return Watch.builder()
                .watchId(entity.getWatchId())
                .product(entity.getProduct())
                .account(entity.getAccount())
                .watchCount(dto.getWatchCount())
                .recentWatch(dto.getRecentWatch())
                .build();
    }
}
