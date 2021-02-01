package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Payment;
import com.ecommerce.j3.domain.network.request.PaymentApiRequest;
import com.ecommerce.j3.domain.network.response.PaymentApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaymentMapper implements DefaultMapper<Payment, PaymentApiRequest, PaymentApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Payment entity, PaymentApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Payment entity, PaymentApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Payment toEntity(PaymentApiRequest dto);

    @Override
    public abstract PaymentApiResponse toDto(Payment entity);
}
