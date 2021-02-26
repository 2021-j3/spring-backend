package com.ecommerce.j3.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    // Account Error
    EmailExists(1001, "이미 존재하는 이메일로 가입/갱신이 시도되었습니다"),
    PhoneNumberExists(1002, "이미 존재하는 핸드폰 번호로 가입/갱신이 시도되었습니다"),
    AccountNotFound(1003, "존재하지 않는 account_id 입니다");
    private final Integer code;
    private final String message;
}

