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
                .sessionId(req.getSessionId() != "" ? req.getSessionId() : db.getSessionId())
                .token(req.getToken() != "" ? req.getToken() : db.getToken())
                .status(req.getStatus() != null ? req.getStatus() : db.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : db.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : db.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : db.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : db.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : db.getUserDiscount())
                .grandTotal(req.getGrandTotal() != null ? req.getGrandTotal() : db.getGrandTotal())
                .firstName(req.getFirstName() != "" ? req.getFirstName() : db.getFirstName())
                .lastName(req.getLastName() != "" ? req.getLastName() : db.getLastName())
                .phoneNumber(req.getPhoneNumber() != "" ? req.getPhoneNumber() : db.getPhoneNumber())
                .email(req.getEmail() != "" ? req.getEmail() : db.getEmail())
                .roadAddress(req.getRoadAddress() != "" ? req.getRoadAddress() : db.getRoadAddress())
                .address(req.getAddress() != "" ? req.getAddress() : db.getAddress())
                .city(req.getCity() != "" ? req.getCity() : db.getCity())
                .province(req.getProvince() != "" ? req.getProvince() : db.getProvince())
                .country(req.getCountry() != "" ? req.getCountry() : db.getCountry())
                .zipCode(req.getZipCode() != null ? req.getZipCode() : db.getZipCode())
                .content(req.getContent() != "" ? req.getContent() : db.getContent())
                .build();
    }

    @Override
    public abstract Cart toEntity(CartApiRequest dto);

    @Override
    public abstract CartApiResponse toDto(Cart entity);
}
