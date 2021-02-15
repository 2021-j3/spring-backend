package com.ecommerce.j3.util;

import antlr.StringUtils;
import com.ecommerce.j3.controller.dto.AccountDto.AccountLoginResponse;
import com.ecommerce.j3.domain.J3UserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenUtil {
    @Value("jwt.token.secret")
    private static final String SECRET_KEY = "secret";
    @Value("jwt.token.expire_at")
    private static final long EXPIRATION_MS = 86400;

    public String parseTokenString(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 자세한 정보는 https://jwt.io
    public String issue(J3UserDetails userDetails) {
        return Jwts.builder() // => 헤더, 페이로드, 키 (https://velopert.com/2389) (https://tools.ietf.org/html/rfc7519#section-4)
                // 헤더 - 타입과 알고리즘 정보
                // 헤더 - 타입(typ)
                .setHeaderParam("typ", "JWT")
                // 페이로드 - 서브젝트(sub)
                .setSubject("j3.com")
                // 페이로드 - 유니큐한 식별자
                // https://stackoverflow.com/questions/47908754/setting-two-subject-in-jwt-token-generation-in-spring-boot-microservice
                .claim("email", userDetails.getUsername())
//                .claim("account_id", userDetails.getId()) // 쓸모 없는 것 같아 제거함
//                .claim("account_authorities", userDetails.getAuthorities())
                // 키 - alg, 키
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 만료시간 (exp)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // 만료시간
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claim = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claim.get("email", String.class);
    }

    public boolean validateToken(String token) {
        System.out.println(token);
        if (!token.equals("")) {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                log.error("Invalid JWT signature");
            } catch (MalformedJwtException e) {
                log.error("Invalid JWT token", e);
            } catch (ExpiredJwtException e) {
                log.error("Expired JWT token", e);
            } catch (UnsupportedJwtException e) {
                log.error("Unsupported JWT token", e);
            } catch (IllegalArgumentException e) {
                log.error("JWT claims string is empty.", e);
            }
        }
        return false;
    }
}