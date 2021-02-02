package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiRequest;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public abstract class OrderItemMapper implements DefaultMapper<OrderItem, OrderItemApiRequest, OrderItemApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget OrderItem entity, OrderItemApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget OrderItem entity, OrderItemApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract OrderItem toEntity(OrderItemApiRequest dto);
    @Override
    public abstract OrderItemApiResponse toDto(OrderItem cart);
}
