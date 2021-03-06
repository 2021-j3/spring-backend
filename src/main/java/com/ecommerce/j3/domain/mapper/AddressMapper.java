package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiRequest;
import com.ecommerce.j3.controller.dto.AddressDto.AddressApiResponse;
import com.ecommerce.j3.domain.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class AddressMapper implements DefaultMapper<Address, AddressApiRequest, AddressApiResponse> {

    @Mapping(source = "accountId", target = "account")
    @Override
    public abstract Address toEntity(AddressApiRequest addressRequest);

    @Override
    public abstract AddressApiResponse toApiResponse(Address address);


    @Override
    public Address updateFromDto(@MappingTarget Address entity, AddressApiRequest dto) {
        if (dto == null) return entity;

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
        return entity;
    }
}
