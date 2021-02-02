package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Product;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.ProductRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CommonMapper.class })
public abstract class AddressMapper implements DefaultMapper<Address, AddressApiRequest, AddressApiResponse> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDto(@MappingTarget Address entity, AddressApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Address entity, AddressApiRequest dto) {
        // TODO: 구현 해야함, account mapper 참조
    }

    @Mapping(source = "accountId", target = "account")
    @Override
    public abstract Address toEntity(AddressApiRequest addressRequest);

//    @Named("addressWithoutRef")
//    @Mapping(target = "account", ignore = true)
//    public abstract Address toEntityWithoutRef(AddressApiRequest addressApiRequest);

    @Override
    public abstract AddressApiResponse toDto(Address address);
}
