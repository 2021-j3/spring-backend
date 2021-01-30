package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    @Mapping(target = "passwordHash", source = "password")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(target = "password", source = "passwordHash")
    AccountDTO toDTO(Account account);

    Account toEntity(AccountDTO.RegisterRequest request);
}
