package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiRequest;
import com.ecommerce.j3.controller.dto.OrderDto.OrderApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderMapper implements DefaultMapper<Order, OrderApiRequest, OrderApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Order entity, OrderApiRequest dto) {
        if (dto == null) return;
        Order db = entity;
        Order req = toEntity(dto);
        entity = Order.builder()
                .ordersId(db.getOrdersId())
                .account(db.getAccount())
                .orderItems(req.getOrderItems())
                .sessionId(req.getSessionId() != "" ? req.getSessionId() : req.getSessionId())
                .token(req.getToken() != "" ? req.getToken() : req.getToken())
                .status(req.getStatus() != null ? req.getStatus() : req.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : req.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : req.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : req.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : req.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : req.getUserDiscount())
                .grandTotal(req.getGrandTotal())
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
    public abstract Order toEntity(OrderApiRequest dto);
    @Override
    public abstract OrderApiResponse toDto(Order cart);
}
