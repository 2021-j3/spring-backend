package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiRequest;
import com.ecommerce.j3.controller.dto.PaymentDto.PaymentApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaymentMapper implements DefaultMapper<Payment, PaymentApiRequest, PaymentApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Payment entity, PaymentApiRequest dto) {
        if (dto == null) return;
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Payment toEntity(PaymentApiRequest dto);

    @Override
    public abstract PaymentApiResponse toDto(Payment entity);
}
