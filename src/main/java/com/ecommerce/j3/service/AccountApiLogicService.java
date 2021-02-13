package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.AccountDto.AccountApiRequest;
import com.ecommerce.j3.controller.dto.AccountDto.AccountApiResponse;
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
public class AccountApiLogicService {

    // 1. request data
    // 2. account 생성
    // 3. 생성된 데이터 -> return AccountApiResponse

    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AccountMapper accountMapper;

    public AccountApiResponse saveAccount(AccountApiRequest request) {
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
        return (AccountApiResponse) accountMapper.toApiResponse(account);
    }

    public AccountApiResponse findAccount(Long id) {
        // 1. id -> repository getOne / getById
        Account accountFromDB = findById(id);
        // 2. return account -> accountApiResponse
        return accountMapper.toApiResponse(accountFromDB);
    }

    public AccountApiResponse findAccountByEmail(String email){
        return accountMapper.toApiResponse(findByEmail(email));
    }

    //    public BodyData<AccountApiResponse> update(BodyData<AccountApiRequest> request) {
    public AccountApiResponse updateAccount(AccountApiRequest request) {
        // 1. repository에서 찾기
        Account accountFromDB = findById(request.getAccountId());
        // 2, mapper로 request에 존재하는 경우 해당 항목을 업데이트
        System.out.println("accountFromDB: " + accountFromDB.getLastName());

        accountMapper.updateFromDto(accountFromDB, request);
        // 3. 수정된 accountFromDB를 repository에 저장
        accountRepository.save(accountFromDB);

        System.out.println("accountRepository: " + accountRepository.findAll());
        // 4. 수정된 accountFromDB를 response로 바꾸어 리턴
        return accountMapper.toApiResponse(accountFromDB);
    }

    public void removeAccount(Long id) {
        accountRepository.deleteById(id);
    }

    // 패키지 한정자, service패키지 내에서만 접근 가능
    Account findById(Long id){
        return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

}
