package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CartMapper implements DefaultMapper<Cart, CartApiRequest, CartApiResponse> {

    @Override
    public abstract Cart toEntity(CartApiRequest dto);

    @Override
    public abstract CartApiResponse toDto(Cart entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Cart entity, CartApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Cart entity, CartApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }


}
