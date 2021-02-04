package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiRequest;
import com.ecommerce.j3.controller.dto.CartItemDto.CartItemApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CartItemMapper implements DefaultMapper<CartItem, CartItemApiRequest, CartItemApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget CartItem entity, CartItemApiRequest dto){
        if (dto == null) return;

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
    }
    
    @Override
    public abstract CartItem toEntity(CartItemApiRequest dto);
    @Override
    public abstract CartItemApiResponse toDto(CartItem cart);
}
