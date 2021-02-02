package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.domain.network.AddressDto.AddressApiRequest;
import com.ecommerce.j3.domain.network.AddressDto.AddressApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper implements DefaultMapper<Address, AddressApiRequest, AddressApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Address entity, AddressApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Address entity, AddressApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Override
    public abstract Address toEntity(AddressApiRequest addressRequest);

    @Override
    public abstract AddressApiResponse toDto(Address address);
}
