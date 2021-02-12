package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AccountDto;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.AccountType;
import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.repository.OrderRepository;
import com.ecommerce.j3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountApiLogicService implements ServiceCrudInterface<AccountApiRequest, AccountApiResponse> {

    // 1. request data
    // 2. account 생성
    // 3. 생성된 데이터 -> return AccountApiResponse

    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountApiResponse save(AccountApiRequest request) {
        // 새로운 유저 타입은 무조건 user 로 고정 ( 혹은 다른 로직 수행 )
        request.builder()
                .accountType(AccountType.USER)
                .build();

        // 1. request -> entity
        Account account = accountMapper.toEntity(request);

//        Stream<Account> stm = account.stream().

        // 2. entity -> repository 에 저장
        accountRepository.save(account);

        // 3. 생성된 데이터 -> return AccountApiResponse
        return (AccountApiResponse) accountMapper.toApiResponseDto(account);
    }

    @Override
    public AccountApiResponse findOne(Long id) {
        // 1. id -> repository getOne / getById
        Account accountFromDB = accountRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        // 2. return account -> accountApiResponse
        return accountMapper.toApiResponseDto(accountFromDB);
    }

    public BodyData<AccountApiResponse> readByEmail(String email) {
        Account account = accountRepository.findByEmail(email).orElseThrow(()->new RuntimeException("cannot find"));
        return response(account);
    }

    @Override
//    public BodyData<AccountApiResponse> update(BodyData<AccountApiRequest> request) {
    public AccountApiResponse update(AccountApiRequest request) {
        // 1. repository에서 찾기
        Account accountFromDB = accountRepository.findById(request.getAccountId())
                .orElseThrow(EntityNotFoundException::new);
        // 2, mapper로 request에 존재하는 경우 해당 항목을 업데이트
        System.out.println("accountFromDB: " + accountFromDB.getLastName());

        accountMapper.updateFromDto(accountFromDB, request);
        // 3. 수정된 accountFromDB를 repository에 저장
        accountRepository.save(accountFromDB);

        System.out.println("accountRepository: " + accountRepository.findAll());
        // 4. 수정된 accountFromDB를 response로 바꾸어 리턴
        return accountMapper.toApiResponseDto(accountFromDB);
    }

    @Override
    public void remove(Long id) {
       accountRepository.deleteById(id);
    }

    private BodyData<AccountApiResponse> response(Account account) {
        AccountApiResponse response = accountMapper.toApiResponseDto(account);
        return BodyData.OK(response);
    }

}
