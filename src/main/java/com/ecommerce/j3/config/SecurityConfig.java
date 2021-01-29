//package com.ecommerce.j3.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .headers()
//                .cacheControl()
//                .and()
//                .frameOptions()
//                .and()
//                .contentTypeOptions()
//                .and()
//                .httpStrictTransportSecurity()
//                .maxAgeInSeconds(31536000)
//                .includeSubDomains(true);
//        http
//                .headers()
//                .contentSecurityPolicy("default-src 'self';")
//                .and()
//                .contentSecurityPolicy("font-src 'self' https://use.typekit.net;")
//                .and()
//                .contentSecurityPolicy(" img-src 'self' https://p.typekit.net https://media.vlpt.us;");
//        http
//                .requiresChannel()
//                .anyRequest()
//                .requiresSecure();
//        http
//                .csrf()
//                .ignoringAntMatchers("/project","/mail");
//    }
//
//}