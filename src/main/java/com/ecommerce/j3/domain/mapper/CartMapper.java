package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CartMapper implements DefaultMapper<Cart, CartApiRequest, CartApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Cart entity, CartApiRequest dto){
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
        Cart db = entity;
        Cart req = toEntity(dto);
        entity = Cart.builder()
                .cartId(db.getCartId())
                .account(db.getAccount())
                .cartItems(req.getCartItems())
                .sessionId(req.getSessionId() != "" ? req.getSessionId() : req.getSessionId())
                .token(req.getToken() != "" ? req.getToken() : req.getToken())
                .status(req.getStatus() != null ? req.getStatus() : req.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : req.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : req.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : req.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : req.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : req.getUserDiscount())
                .grandTotal(req.getGrandTotal() != null ? req.getGrandTotal() : req.getGrandTotal())
                .firstName(req.getFirstName() != "" ? req.getFirstName() : req.getFirstName())
                .lastName(req.getLastName() != "" ? req.getLastName() : req.getLastName())
                .phoneNumber(req.getPhoneNumber() != "" ? req.getPhoneNumber() : req.getPhoneNumber())
                .email(req.getEmail() != "" ? req.getEmail() : req.getEmail())
                .roadAddress(req.getRoadAddress() != "" ? req.getRoadAddress() : req.getRoadAddress())
                .address(req.getAddress() != "" ? req.getAddress() : req.getAddress())
                .city(req.getCity() != "" ? req.getCity() : req.getCity())
                .province(req.getProvince() != "" ? req.getProvince() : req.getProvince())
                .country(req.getCountry() != "" ? req.getCountry() : req.getCountry())
                .zipCode(req.getZipCode() != null ? req.getZipCode() : req.getZipCode())
                .content(req.getContent() != "" ? req.getContent() : req.getContent())
                .build();
    }

    @Override
    public abstract Cart toEntity(CartApiRequest dto);

    @Override
    public abstract CartApiResponse toDto(Cart entity);
}
