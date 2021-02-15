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
package com.ecommerce.j3.config;

import com.ecommerce.j3.service.AccountApiLogicService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
// import org.springframework.session.web.http.HeaderHttpSessionStrategy;
// import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    AccountApiLogicService accountService;

    @Bean // 시스템 공용으로 등록
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Spring 에서 기본으로 제공하는 인증객체를 사용
        return super.authenticationManagerBean();
    }

    /**
     * custom userdetailservice (AccountApiLogicService)를 사용하므로
     * AuthenticationManagerBuilder에 해당 클래스를 주입
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    /**
     * x-auth-token 삽입
     * @return { HeaderHttpSessionStrategy } configure 에서 정의한 HeaderHttpSessionStrategy
     */
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    @Override
    public void configure(WebSecurity webSecurity){
        // static 디렉터리
        webSecurity.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 스프링 세션을 사용,
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers("/api/accounts/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/accounts/test").hasAuthority("ROLE_USER")
                .antMatchers("/**").permitAll()
                .and()
                .logout()
                //                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/auth/denied");
    }
}
