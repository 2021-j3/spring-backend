package com.ecommerce.j3.domain.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    void updateFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    @Mapping(target = "passwordHash", source = "password")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(target = "password", source = "passwordHash")
    AccountDTO toDTO(Account account);
}
