package com.ecommerce.j3.domain.mapper;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommonMapper.class})
public abstract class AccountMapper implements DefaultMapper<Account, AccountDto.AccountApiRequest, AccountDto.AccountApiResponse>{

    @Mapping(source="password", target="passwordHash")
    @Override
    public abstract Account toEntity(AccountDto.AccountApiRequest dto);

    @Override
    public abstract AccountDto.AccountApiResponse toApiResponse(Account entity);

    public abstract AccountDto.AccountApiRequest toRequestDto(AccountDto.UpdateAccountRequest dtoWithSomeField);
    public abstract AccountDto.AccountApiRequest toRequestDto(AccountDto.CreateAccountRequest dtoWithSomeField);
    public abstract AccountDto.CreateAccountResponse toCreateAccountResponse(Account account);

    @Override
    public Account updateFromDto(@MappingTarget Account entity, AccountDto.AccountApiRequest dto){
        if (dto == null) return entity;
        Account db = entity;
        entity = Account.builder()
                // db 값만 존재
                .accountId(db.getAccountId())
                .registeredAt(db.getRegisteredAt())
                .lastLogin(db.getLastLogin())
                // 필수 값, 입력된 값이 null일 경우, 기존 값을 사용
                .defaultAddress(dto.getDefaultAddress()!=null? dto.getDefaultAddress() : db.getDefaultAddress())
                .email((dto.getEmail()!=null && !dto.getEmail().equals("")) ? dto.getEmail() : db.getEmail())
                .passwordHash((dto.getPassword()!=null && !dto.getPassword().equals("")) ? dto.getPassword() : db.getPasswordHash())
                .firstName((dto.getFirstName()!=null && !dto.getFirstName().equals("")) ? dto.getFirstName() : db.getFirstName())
                .lastName((dto.getLastName()!=null && !dto.getLastName().equals("")) ? dto.getLastName() : db.getLastName())
                .gender(dto.getGender() != null ? dto.getGender() : db.getGender())
                .accountType(dto.getAccountType() != null ? dto.getAccountType() : db.getAccountType())
                // 필수 아님, null 가능
                .birthday(dto.getBirthday())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        return entity;
    }
}
