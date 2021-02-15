/**
 * spring security 에서 token 로그인을 사용하기 위해 사용
 * 현재 버전(2021-02-15)은 session에도 로그인 정보를 저장하므로, SecurityConfig.class 에서
 * SessionCreationPolicy.NEVER 를 SessionCreationPolicy.ALWAYS 로 바꿔주면 csrf문제가 없으므로 주석처리해도 됨
 */
package com.ecommerce.j3.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-auth-token, content-type");

        chain.doFilter(req, response);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}