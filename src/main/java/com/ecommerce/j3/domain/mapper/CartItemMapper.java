package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiRequest;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class CartItemMapper implements DefaultMapper<CartItem, CartItemApiRequest, CartItemApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget CartItem entity, CartItemApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget CartItem entity, CartItemApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "cartId", target = "cart")
    @Override
    public abstract CartItem toEntity(CartItemApiRequest dto);
    @Override
    public abstract CartItemApiResponse toDto(CartItem cart);
}
