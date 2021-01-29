package com.ecommerce.j3.domain.network;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 생성자 생성을 위한 어노테이션

public class BodyData<T> {
    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private Integer statusCode;

    // api 부가 설명
    private String description;

    private T data;

    // OK
    public static <T> BodyData<T> OK() {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .statusCode(200)
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> BodyData<T> OK(T data) {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .statusCode(200)
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> BodyData<T> ERROR(String description) {
        // 생성자
        return (BodyData<T>) BodyData.builder()
                .transactionTime(LocalDateTime.now())
                .statusCode(404)
                .description(description)
                .build();
    }
}
