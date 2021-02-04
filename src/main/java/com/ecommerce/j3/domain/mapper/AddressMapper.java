package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper implements DefaultMapper<Address, AddressApiRequest, AddressApiResponse> {

    @Override
    public void updateFromDto(@MappingTarget Address entity, AddressApiRequest dto) {
        if (dto == null) return;

        Address db = entity;
        // Address req = toEntity(dto);
        entity = Address.builder()
                .addressId(db.getAddressId())
                // .account(req.getAccount() ? req.getAccount() : db.getAccount()) // 사실 상 바꿀 이유가 없음
                .account(db.getAccount())
                .address(dto.getAddress() != null ? dto.getAddress() : db.getAddress())
                .roadAddress(dto.getRoadAddress() != null ? dto.getRoadAddress() : db.getRoadAddress())
                .city(dto.getCity() != null ? dto.getCity() : db.getCity())
                .province(dto.getProvince() != null ? dto.getProvince() : db.getProvince())
                .country(dto.getCountry() != null ? dto.getCountry() : db.getCountry())
                .zipCode(dto.getZipCode() != null ? dto.getZipCode() : db.getZipCode())
                .build();
    }

    @Override
    public abstract Address toEntity(AddressApiRequest addressRequest);

    @Override
    public abstract AddressApiResponse toDto(Address address);
}
