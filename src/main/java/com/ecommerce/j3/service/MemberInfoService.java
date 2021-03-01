package com.ecommerce.j3.service;

import com.ecommerce.j3.domain.mapper.AccountMapper;
import com.ecommerce.j3.repository.AccountRepository;
import com.ecommerce.j3.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtTokenUtil jwtTokenUtil;

}
