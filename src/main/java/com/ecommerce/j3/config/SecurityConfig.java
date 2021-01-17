package com.ecommerce.j3.config;

import com.ecommerce.j3.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    AccountService accountService;

    @Bean // 시스템 공용으로 등록
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Override
    public void configure(WebSecurity webSecurity){
        // static 디렉터리
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/**").permitAll()
                .and()
            .formLogin()
//                .loginPage("/auth/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/auth/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }
}
