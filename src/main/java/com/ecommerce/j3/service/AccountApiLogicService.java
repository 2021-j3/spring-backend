package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.api.CrudInterface;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;

import com.ecommerce.j3.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountApiLogicService implements CrudInterface<AccountApiRequest, AccountApiResponse> {

    // 1. request data
    // 2. account 생성
    // 3. 생성된 데이터 -> return AccountApiResponse

    private AccountRepository accountRepository;

    @Autowired
    public AccountApiLogicService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public BodyData<AccountApiResponse> create(BodyData<AccountApiRequest> request) {

        // 1. request data
        AccountApiRequest accountApiRequest = request.getData();

        // 2. account 생성

        Account account = Account.builder().
                accountId(accountApiRequest.getAccountId()).
                email(accountApiRequest.getEmail()).
                passwordHash(accountApiRequest.getPasswordHash()).
                firstName(accountApiRequest.getFirstName()).
                lastName(accountApiRequest.getLastName()).
                gender(accountApiRequest.getGender()).
                birthday(accountApiRequest.getBirthday()).
                phoneNumber(accountApiRequest.getPhoneNumber()).
                registeredAt(accountApiRequest.getRegisteredAt()).
                lastLogin(accountApiRequest.getLastLogin()).
                accountType(accountApiRequest.getAccountType()).
                build();

//        Account newAccount = accountRepository.save(account);
        accountRepository.save(account);
        // 3. 생성된 데이터 -> return AccountApiResponse
//        return response(newAccount);
        return response(account);
    }

    @Override
    public BodyData<AccountApiResponse> read(Long id) {
        // 1. id -> repository getOne / getById
        Optional<Account> optional = accountRepository.findById(id);

        // 2. return account -> accountApiResponse
        return optional.map(account -> response(account))
                .orElseGet(()-> BodyData.ERROR("데이터 없음"));

    }

    @Override
    public BodyData<AccountApiResponse> update(BodyData<AccountApiRequest> request) {

        // 1. data 생성
        AccountApiRequest accountApiRequest = request.getData();
        // 2. id -> account 찾고
        Optional<Account> optional = accountRepository.findById(accountApiRequest.getAccountId());

        return optional.map(account -> {
            // 3. update
            Account account2 = Account.builder().
                    accountId(account.getAccountId()).
                    email(accountApiRequest.getEmail()).
                    passwordHash(accountApiRequest.getPasswordHash()).
                    firstName(accountApiRequest.getFirstName()).
                    lastName(accountApiRequest.getLastName()).
                    gender(accountApiRequest.getGender()).
                    birthday(accountApiRequest.getBirthday()).
                    phoneNumber(accountApiRequest.getPhoneNumber()).
                    registeredAt(accountApiRequest.getRegisteredAt()).
                    lastLogin(accountApiRequest.getLastLogin()).
                    accountType(accountApiRequest.getAccountType()).
                    build();
//            account
//                    .setEmail(accountApiRequest.getEmail())
//                    .setPasswordHash(accountApiRequest.getPasswordHash())
//                    .setFirstName(accountApiRequest.getFirstName())
//                    .setLastName(accountApiRequest.getLastName())
//                    .setGender(accountApiRequest.getGender())
//                    .setBirthday(accountApiRequest.getBirthday())
//                    .setPhoneNumber(accountApiRequest.getPhoneNumber())
////                    .setRegisteredAt(accountApiRequest.getRegisteredAt())
////                    .setLastLogin(accountApiRequest.getLastLogin())
//                    .setAccountType(accountApiRequest.getAccountType())
            ;
            return account2;
        })
                .map(account -> accountRepository.save(account))
                .map(updateAccount -> response(updateAccount))
                .orElseGet(() -> BodyData.ERROR("데이터 없음"));
    }
    @Override
    public BodyData delete(Long id) {
        Optional<Account> optional = accountRepository.findById(id);

        // 2. repository -> delete
        return optional.map(account -> {
            accountRepository.delete(account);

            return BodyData.OK();

        })
                .orElseGet(()-> BodyData.ERROR("데이터 없음"));
    }

    private BodyData<AccountApiResponse> response(Account account){
        AccountApiResponse accountApiResponse = AccountApiResponse.builder().
                accountId(account.getAccountId()).
                email(account.getEmail()).
                passwordHash(account.getPasswordHash()).
                firstName(account.getFirstName()).
                lastName(account.getLastName()).
                gender(account.getGender()).
                birthday(account.getBirthday()).
                phoneNumber(account.getPhoneNumber()).
                registeredAt(account.getRegisteredAt()).
                lastLogin(account.getLastLogin()).
                accountType(account.getAccountType()).
                build();

        return BodyData.OK(accountApiResponse);
    }

}
