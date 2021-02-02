package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiRequest;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public abstract class OrderMapper implements DefaultMapper<Order, OrderApiRequest, OrderApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Order entity, OrderApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Order entity, OrderApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Order toEntity(OrderApiRequest dto);
    @Override
    public abstract OrderApiResponse toDto(Order cart);
}
