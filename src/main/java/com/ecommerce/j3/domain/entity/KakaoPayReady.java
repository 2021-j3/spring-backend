package com.ecommerce.j3.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class KakaoPayReady {
    //response
    private String tid;

    private String nextRedirectPcUrl;

    private Date createdAt;
}
