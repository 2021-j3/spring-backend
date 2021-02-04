package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
import com.ecommerce.j3.controller.dto.AccountDto.UpdateAccountRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper implements DefaultMapper<Account, AccountApiRequest, AccountApiResponse>{

    @Override
    public void updateFromDto(@MappingTarget Account entity, AccountApiRequest dto){
        if (dto == null) return;
        Account db = entity;
        entity = Account.builder()
                // db 값만 존재
                .accountId(db.getAccountId())
                .registeredAt(db.getRegisteredAt())
                .lastLogin(db.getLastLogin())
                .defaultAddress(db.getDefaultAddress())
                // 필수 값, 입력된 값이 null일 경우, 기존 값을 사용
                .email(!dto.getEmail().equals("") ? dto.getEmail() : db.getEmail())
                .passwordHash(!dto.getPasswordHash().equals("") ? dto.getPasswordHash() : db.getPasswordHash())
                .firstName(!dto.getFirstName().equals("") ? dto.getFirstName() : db.getFirstName())
                .lastName(!dto.getLastName().equals("") ? dto.getLastName() : db.getLastName())
                .gender(dto.getGender() != null ? dto.getGender() : db.getGender())
                .accountType(dto.getAccountType() != null ? dto.getAccountType() : db.getAccountType())
                // 필수 아님, null 가능
                .birthday(dto.getBirthday())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public abstract AccountApiRequest toDto(UpdateAccountRequest dtoWithSomeField);

    //    @Mapping(target = "passwordHash", source = "password")
//    public abstract Account toEntity(AccountDTO dto);
    @Override
    public abstract AccountApiResponse toDto(Account entity);

//    public abstract Account toEntity(AccountDTO.RegisterRequest dto);

    @Override
    public abstract Account toEntity(AccountApiRequest dto);
}
