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
    public abstract Watch updateFromDto(@MappingTarget Watch entity, WatchApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Watch entity, WatchApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }
}
