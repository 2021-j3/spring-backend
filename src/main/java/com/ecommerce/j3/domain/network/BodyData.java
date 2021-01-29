package com.ecommerce.j3.domain.network;

import lombok.*;

import java.time.LocalDateTime;
@SuppressWarnings("unchecked")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 생성자 생성을 위한 어노테이션
public class BodyData<T> {
    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    private T data;

    // OK
    public static <T> BodyData<T> OK() {
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }


    // DATA OK
    public static <T> BodyData<T> OK(T data) {
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> BodyData<T> ERROR(String description) {
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}