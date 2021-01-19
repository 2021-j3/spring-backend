package com.ecommerce.j3.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    void updateFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "birthday", source = "birthday")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(target = "password", source = "passwordHash")
    @Mapping(target = "birthday", source = "birthday")
    AccountDTO toDTO(Account account);

    default LocalDate map(LocalDateTime localDateTime){
        return localDateTime == null ? null : localDateTime.toLocalDate();
    }

    default LocalDateTime map(LocalDate localDate){
        return localDate == null ? null : localDate.atStartOfDay();
    }
}