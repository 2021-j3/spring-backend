package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.network.Header;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import com.ecommerce.j3.service.AccountApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {

    private AccountApiLogicService accountApiLogicService;

    public AccountApiController(AccountApiLogicService accountApiLogicService){
        this.accountApiLogicService = accountApiLogicService;
    }


    @Override
    @PostMapping("")
    public Header<AccountApiResponse> create(@RequestBody Header<AccountApiRequest> request) {
        log.info("{}",request);
        return accountApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AccountApiResponse> read(@PathVariable Long id) {
        log.info("read id: {}", id);
        return accountApiLogicService.read(id);
    }

    @Override
    @PutMapping("{id}")
    public Header<AccountApiResponse> update(@RequestBody Header<AccountApiRequest> request) {
        return accountApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("delete : {}",id);
        return accountApiLogicService.delete(id);
    }
}
