package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.controller.dto.CartItemDto;
import com.ecommerce.j3.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class CartItemMapper implements DefaultMapper<CartItem, CartItemDto.CartItemApiRequest, CartItemDto.CartItemApiResponse> {

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "cartId", target = "cart")
    @Override
    public abstract CartItem toEntity(CartItemDto.CartItemApiRequest dto);

    @Override
    public abstract CartItemDto.CartItemApiResponse toApiResponseDto(CartItem cart);


    @Override
    public CartItem updateFromDto(@MappingTarget CartItem entity, CartItemDto.CartItemApiRequest dto){
        if (dto == null) return null;

        CartItem db = entity;
        CartItem req = toEntity(dto);
        entity = CartItem.builder()
                // 바뀌면 안되는 값
                .cartItemId(db.getCartItemId())
                .product(db.getProduct())
                .cart(req.getCart() != null ? req.getCart() : db.getCart())
                .sku(dto.getSku() != null ? dto.getSku() : db.getSku())
                .price(dto.getPrice() != null ? dto.getPrice() : db.getPrice())
                .discountRate(dto.getDiscountRate() != null ? dto.getDiscountRate() : db.getDiscountRate())
                .quantity(dto.getQuantity() != null ? dto.getQuantity() : db.getQuantity())
                .active(dto.getActive() != null ? dto.getActive() : db.getActive())
                .content(dto.getContent() != null ? dto.getContent() : db.getContent())
                // 업데이트에 상관없이 갱신되는 값
                .updatedAt(db.getUpdatedAt())
                .build();
        return entity;
    }
}
