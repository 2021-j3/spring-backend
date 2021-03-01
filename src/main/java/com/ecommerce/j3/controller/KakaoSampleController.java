package com.ecommerce.j3.controller;//package com.ecommerce.j3.controller;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.service.KakaoPayService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KakaoSampleController {
    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady(); // 여기에 OrderApiRequest 넣어야 됨.

    }
//    @PostMapping("/kakaoPay")
//    public String kakaoPay(OrderDto.OrderApiRequest request) {
//        log.info("kakaoPay post............................................");
//
//        return "redirect:" + kakaopay.kakaoPayReady(request); // 여기에 OrderApiRequest 넣어야 됨.
//
//    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

    }

}
