package com.ecommerce.j3.config;

import com.ecommerce.j3.filters.JwtTokenFilter;
import com.ecommerce.j3.service.AccountApiLogicService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 2021-03-07 penguin: PreAuthorize() 를 사용할 수 있습니다!
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccountApiLogicService accountService;
    private final JwtTokenFilter jwtTokenFilter;

    /**
     * 공용 password encoder 등록
     * TODO: Hash 함수 생성
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    /**
     * Spring 에서 기본으로 제공하는 인증객체를 등록
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 공용 httpSessionStrategy 등록, x-auth-token 삽입
     * @return { HeaderHttpSessionStrategy } configure 에서 정의한 HeaderHttpSessionStrategy
     */
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
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
                .authorizeRequests()
                .antMatchers("/api/accounts/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/accounts/test").hasAuthority("ROLE_USER")
                .antMatchers("/**").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                //                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/auth/denied");
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
