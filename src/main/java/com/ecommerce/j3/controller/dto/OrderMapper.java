//package com.ecommerce.j3.controller.dto;
//
//import com.ecommerce.j3.domain.entity.Order;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//public interface OrderMapper {
//    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
//
//    @Mapping(source = "accountId", target = "account")
//    OrderDto orderEntityToOrderDto(Order order);
//
//    void updateFromDto(Order orderFromDB, OrderDto.OrderApiRequest request);
//}
