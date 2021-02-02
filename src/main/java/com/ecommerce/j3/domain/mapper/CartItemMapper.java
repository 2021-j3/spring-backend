package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiRequest;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public abstract class CartItemMapper implements DefaultMapper<CartItem, CartItemApiRequest, CartItemApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget CartItem entity, CartItemApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget CartItem entity, CartItemApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }
    
    @Override
    public abstract CartItem toEntity(CartItemApiRequest dto);
    @Override
    public abstract CartItemApiResponse toDto(CartItem cart);
}
