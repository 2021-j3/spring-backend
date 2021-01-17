package com.ecommerce.j3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountControllerTest {
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;
    @Before
    public void beforeAll(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    ///////// Spring Security 테스트 템플릿 //////////

    @Test
    void getRegister() {
    }

    @Test
    void postRegister() {
    }

    @Test
    @WithMockUser(roles = "USER")
    void getInfo() throws Exception {
        mvc.perform(
                get("localhost:9999/user/info")
                        .contentType(MediaType.TEXT_HTML)
        ).andExpect(status().isOk());
    }
}