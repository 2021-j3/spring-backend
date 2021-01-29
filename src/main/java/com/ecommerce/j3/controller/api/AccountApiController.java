package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import com.ecommerce.j3.service.AccountApiLogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Account"})
@Slf4j
@RestController
@RequestMapping("/api/accounts")
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {
    private AccountApiLogicService accountApiLogicService;

    public AccountApiController(AccountApiLogicService accountApiLogicService){
        this.accountApiLogicService = accountApiLogicService;
    }

    @ApiOperation(value = "회원 추가", notes = "회원을 추가한다.")
    @PostMapping("")
    @Override
    public BodyData<AccountApiResponse> create(@RequestBody BodyData<AccountApiRequest> request) {
        log.info("{}",request);
        return accountApiLogicService.create(request);
    }

    @ApiOperation(value = "회원 조회", notes = "id에 해당하는 회원을 조회한다.")
    @GetMapping("{id}")
    @Override
    public BodyData<AccountApiResponse> read(@PathVariable Long id) {
        log.info("read id: {}", id);
        return accountApiLogicService.read(id);
    }

    @ApiOperation(value = "회원 수정", notes = "회원을 수정한다.")
    @PutMapping("{id}")
    @Override
    public BodyData<AccountApiResponse> update(@RequestBody BodyData<AccountApiRequest> request) {
        return accountApiLogicService.update(request);
    }
    
    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("{id}")
    @Override
    public BodyData delete(@PathVariable Long id) {
        log.info("delete : {}",id);
        return accountApiLogicService.delete(id);
    }
}