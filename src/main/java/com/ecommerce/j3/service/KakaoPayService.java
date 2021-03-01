package com.ecommerce.j3.service;//package com.ecommerce.j3.service;

import com.ecommerce.j3.controller.dto.OrderDto;
import com.ecommerce.j3.domain.entity.KakaoPayReady;
import com.ecommerce.j3.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
@Transactional
public class KakaoPayService {
    private static final String HOST = "https://kapi.kakao.com";

    @Autowired
    private OrderRepository orderRepository;


    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    private KakaoPayReady kakaoPayReady;
    public String kakaoPayReady(){
        RestTemplate restTemplate = new RestTemplate();

        // header info
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "52ca6c55ab85d3d319541aeb17d5186d");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // body info
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME"); // 실거래할 때 카카오가 주는 cid
        params.add("tid", kakaoPayReady.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "jusnu");
        params.add("item_name", "갤럭시S21");
        params.add("quantity", "1");
        params.add("total_amount", "2100");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:9876/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:9876/kakaoPayCancel");
        params.add("fail_url", "http://localhost:9876/kakaoPaySuccessFail");

        // header + body info
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);

            log.info("{}", kakaoPayReady);
            return kakaoPayReady.getNextRedirectPcUrl();
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "/pay";
    }
//    public String kakaoPayReady(OrderDto.OrderApiRequest request){
//        RestTemplate restTemplate = new RestTemplate();
//
//        // header info
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "KakaoAK " + "52ca6c55ab85d3d319541aeb17d5186d");
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
//
//        // body info
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("cid", "TC0ONETIME");
//        params.add("tid", kakaoPayReady.getTid());
//        params.add("partner_order_id", String.valueOf(request.getOrdersId()));
//        params.add("partner_user_id", String.valueOf(request.getAccountId()));
//        params.add("item_name", String.valueOf(request.getContent()));      // item name
//        params.add("quantity", String.valueOf(request.getOrderItemIds().get(1)));                                        // 얘는 어디서?
//        params.add("total_amount", String.valueOf(request.getPayInfo().getItemPriceTotal()));
//        params.add("tax_free_amount", String.valueOf(request.getPayInfo().getTax()));
//        params.add("approval_url", "http://localhost:9876/kakaoPaySuccess");
//        params.add("cancel_url", "http://localhost:9876/kakaoPayCancel");
//        params.add("fail_url", "http://localhost:9876/kakaoPaySuccessFail");
//
//        // header + body info
//        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//
//        try {
//            kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);
//
//            log.info("{}", kakaoPayReady);
//            return kakaoPayReady.getNextRedirectPcUrl();
//        } catch (RestClientException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "/pay";
//    }

}
