package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper implements DefaultMapper<Account, AccountApiRequest, AccountApiResponse>{
    public abstract void updateFromDto(@MappingTarget Account entity, AccountApiRequest dto);

    @AfterMapping
    protected void afterUpdateFromDto(@MappingTarget Account entity, AccountApiRequest dto){
        Account db = entity;
        entity = Account.builder()
                .accountId(db.getAccountId())
                .email(!dto.getEmail().equals("") ? dto.getEmail() : db.getEmail())
                .passwordHash(!dto.getPasswordHash().equals("") ? dto.getPasswordHash() : db.getPasswordHash())
                .firstName(dto.getFirstName().equals("") ? dto.getFirstName() : db.getFirstName())
                .lastName(dto.getLastName().equals("") ? dto.getLastName() : db.getLastName())
                .birthday(dto.getBirthday() != null ? dto.getBirthday() : db.getBirthday())
                .gender(dto.getGender() != null ? dto.getGender() : db.getGender())
                .phoneNumber(dto.getPhoneNumber())
                .accountType(dto.getAccountType() != null ? dto.getAccountType() : db.getAccountType())
                .registeredAt(db.getRegisteredAt())
                .lastLogin(db.getLastLogin())
                .default_address(db.getDefault_address())
                .build();
    }

//    @Mapping(target = "passwordHash", source = "password")
//    public abstract Account toEntity(AccountDTO dto);
    @Override
    public abstract AccountApiResponse toDto(Account entity);

//    public abstract Account toEntity(AccountDTO.RegisterRequest dto);

    @Override
    public abstract Account toEntity(AccountApiRequest dto);
}
