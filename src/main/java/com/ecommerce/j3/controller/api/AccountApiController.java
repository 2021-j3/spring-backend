package com.ecommerce.j3.controller.api;

import com.ecommerce.j3.domain.network.BodyData;
import com.ecommerce.j3.domain.network.request.AccountApiRequest;
import com.ecommerce.j3.domain.network.response.AccountApiResponse;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
public class AccountApiController implements CrudInterface<AccountApiRequest, AccountApiResponse> {

    private AccountApiLogicService accountApiLogicService;

    public AccountApiController(AccountApiLogicService accountApiLogicService){
        this.accountApiLogicService = accountApiLogicService;
    }


    @Override
    @PostMapping("")
    public BodyData<AccountApiResponse> create(@RequestBody BodyData<AccountApiRequest> request) throws JsonProcessingException {
        log.info("{}",request);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(request));

        return accountApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public BodyData<AccountApiResponse> read(@PathVariable Long id) {
        log.info("read id: {}", id);
        return accountApiLogicService.read(id);
    }

    @Override
    @PutMapping("{id}")
    public BodyData<AccountApiResponse> update(@RequestBody BodyData<AccountApiRequest> request) {
        return accountApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public BodyData delete(@PathVariable Long id) {
        log.info("delete : {}",id);
        return accountApiLogicService.delete(id);
    }
}
