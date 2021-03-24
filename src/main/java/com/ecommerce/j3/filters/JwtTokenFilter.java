package com.ecommerce.j3.filters;

import com.ecommerce.j3.domain.J3UserDetails;
import com.ecommerce.j3.service.AccountApiLogicService;
import com.ecommerce.j3.util.CookieUtil;
import com.ecommerce.j3.util.JwtTokenUtil;
import com.ecommerce.j3.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final AccountApiLogicService accountApiLogicService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        final Cookie accessTokenCookie = CookieUtil.getCookie(req, JwtTokenUtil.ACCESS_TOKEN_NAME);
        String email = null;
        if(accessTokenCookie != null) {
            try {
                final String accessToken = accessTokenCookie.getValue();
                jwtTokenUtil.validateToken(accessToken);
                email = jwtTokenUtil.getEmailFromToken(accessToken);
            } catch (ExpiredJwtException e) {
                final String newAccessToken = getNewAccessTokenIfPossible(req, res);
                if (newAccessToken != null)
                    email = jwtTokenUtil.getEmailFromToken(newAccessToken);
            } catch (Exception ignored) {
            }
        }

        if (email != null){
            J3UserDetails userDetails = accountApiLogicService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(req, res);
    }

    private String getNewAccessTokenIfPossible(HttpServletRequest req, HttpServletResponse res){
        final Cookie refreshTokenCookie = CookieUtil.getCookie(req, JwtTokenUtil.REFRESH_TOKEN_NAME);
        String accessToken = null;
        if (refreshTokenCookie != null){
            // 2021-03-07 리프레시 토큰이 redis 에 존재하면 검증할 필요없음
            String refreshToken = refreshTokenCookie.getValue();
            String emailFromToken = jwtTokenUtil.getEmailFromToken(refreshToken);
            String emailFromRedis = redisUtil.getData(refreshToken);
            if (emailFromToken != null && emailFromToken.equals(emailFromRedis)){
                J3UserDetails userDetails = accountApiLogicService.loadUserByUsername(emailFromToken);
                accessToken = jwtTokenUtil.issueAccessToken(userDetails);
                CookieUtil.addCookie(res, JwtTokenUtil.ACCESS_TOKEN_NAME, accessToken, JwtTokenUtil.ACCESS_EXPIRATION_MS);

            }
        }
        return accessToken;
    }
}
