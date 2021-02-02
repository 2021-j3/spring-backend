package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.WatchDto.WatchApiRequest;
import com.ecommerce.j3.controller.dto.WatchDto.WatchApiResponse;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.domain.entity.Watch;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class WatchMapper implements DefaultMapper<Watch, WatchApiRequest, WatchApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Watch entity, WatchApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Watch entity, WatchApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "productId", target = "product")
    @Override
    public abstract Watch toEntity(WatchApiRequest dto);

    @Override
    public abstract WatchApiResponse toDto(Watch entity);
}
