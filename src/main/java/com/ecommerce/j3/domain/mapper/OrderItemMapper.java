package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiRequest;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderItemMapper implements DefaultMapper<OrderItem, OrderItemApiRequest, OrderItemApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget OrderItem entity, OrderItemApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract OrderItem toEntity(OrderItemApiRequest dto);
    @Override
    public abstract OrderItemApiResponse toDto(OrderItem cart);
}
