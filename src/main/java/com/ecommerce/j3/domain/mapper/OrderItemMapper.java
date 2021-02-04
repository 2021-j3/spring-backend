package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiRequest;
import com.ecommerce.j3.controller.dto.OrderItemDto.OrderItemApiResponse;
import com.ecommerce.j3.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class OrderItemMapper implements DefaultMapper<OrderItem, OrderItemApiRequest, OrderItemApiResponse> {

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "orderId", target = "order")
    @Override
    public abstract OrderItem toEntity(OrderItemApiRequest dto);

    @Override
    public abstract OrderItemApiResponse toDto(OrderItem cart);

    @Override
    public void updateFromDto(@MappingTarget OrderItem entity, OrderItemApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }
}
