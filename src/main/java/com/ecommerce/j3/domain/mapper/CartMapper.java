package com.ecommerce.j3.domain.mapper;


import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import com.ecommerce.j3.domain.entity.CartItem;
import com.ecommerce.j3.domain.entity.OrderItem;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderItemRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
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


    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "cartItemIds", target = "cartItems")
    @Override
    public abstract Cart toEntity(CartApiRequest dto);

}
