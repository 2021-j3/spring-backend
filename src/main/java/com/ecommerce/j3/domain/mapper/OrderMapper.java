package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Order;
import com.ecommerce.j3.controller.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class OrderMapper implements DefaultMapper<Order, OrderDto.OrderApiRequest, OrderDto.OrderApiResponse> {

    // target ===> Entity에 있는 필드 이름
    // source ===> Dto에 있는 필드 이름
    // https://stackoverflow.com/questions/45037502/mapstruct-update-existing-field-of-entity-using-data-from-dto
    @Mapping(source = "account", target = "account")
    @Mapping(source = "orderItemIds", target = "orderItems")
    @Override
    public abstract Order toEntity(OrderDto.OrderApiRequest dto);

//    public abstract Order toEntity(OrderDto.OrderCreateRequestByProduct dto);

    @Override
    public abstract OrderDto.OrderApiResponse toApiResponse(Order entity);

    public abstract OrderDto.OrderItemCreateResponseByProduct toApiProductResponse(Order entity);

    public abstract OrderDto.OrderItemCreateResponseByCartItem toApiCartResponse(Order entity);

    public Order updateUserFromDto(@MappingTarget Order entity, OrderDto.OrderApiRequest dto){
        if (dto.equals(null)) return entity;
        Order db = entity;
        Order req = toEntity(dto);
        entity = Order.builder()
                .ordersId(db.getOrdersId())
                .account(db.getAccount())
                .orderItems(db.getOrderItems())
                .sessionId(db.getSessionId())
                .token(db.getToken())
                .status(db.getStatus())
                .itemPriceTotal(db.getItemPriceTotal())
                .itemDiscount(db.getItemDiscount())
                .tax(db.getTax())
                .shipping(db.getShipping())
                .userDiscount(db.getUserDiscount())
                .grandTotal(db.getGrandTotal())
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
                .content(db.getContent())
                .build();
        return entity;
    }

    public Order updatePayFromDto(@MappingTarget Order entity, OrderDto.OrderApiRequest dto){
        if (dto.equals(null)) return entity;
        Order db = entity;
        Order req = toEntity(dto);
        entity = Order.builder()
                .ordersId(db.getOrdersId())
                .account(db.getAccount())
                .orderItems(db.getOrderItems())
                .sessionId(db.getSessionId())
                .token(db.getToken())
                .status(db.getStatus())
                .itemPriceTotal(req.getItemPriceTotal() != null ? req.getItemPriceTotal() : req.getItemPriceTotal())
                .itemDiscount(req.getItemDiscount() != null ? req.getItemDiscount() : req.getItemDiscount())
                .tax(req.getTax() != null ? req.getTax() : req.getTax())
                .shipping(req.getShipping() != null ? req.getShipping() : req.getShipping())
                .userDiscount(req.getUserDiscount() != null ? req.getUserDiscount() : req.getUserDiscount())
                .grandTotal(req.getGrandTotal())
                .firstName(db.getFirstName())
                .lastName(db.getLastName())
                .phoneNumber(db.getPhoneNumber())
                .email(db.getEmail())
                .roadAddress(db.getRoadAddress())
                .address(db.getAddress())
                .city(db.getCity())
                .province(db.getProvince())
                .country(db.getCountry())
                .zipCode(db.getZipCode())
                .content(db.getContent())
                .build();
        return entity;
    }


    @Override
    public Order updateFromDto(@MappingTarget Order entity, OrderDto.OrderApiRequest dto) {
        if (dto == null) return entity;
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
        return entity;
    }

}