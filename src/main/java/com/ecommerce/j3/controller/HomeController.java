package com.ecommerce.j3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    public static void main(){

    }
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }
}
