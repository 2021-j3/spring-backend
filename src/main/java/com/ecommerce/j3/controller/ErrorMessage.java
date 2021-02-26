package com.ecommerce.j3.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityNotFoundException;

@Getter @Setter
public class ErrorMessage {
    private Integer code;
    private String message;
    public ErrorMessage(ErrorType errorType){
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
    }
}
