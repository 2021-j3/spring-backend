package com.ecommerce.j3.domain.mapper;


import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiRequest;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class PaymentMapper implements DefaultMapper<Payment, PaymentApiRequest, PaymentApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Payment entity, PaymentApiRequest dto) {
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "orderId", target = "order")
    @Override
    public abstract Payment toEntity(PaymentApiRequest dto);

    @Override
    public abstract PaymentApiResponse toDto(Payment entity);
}
