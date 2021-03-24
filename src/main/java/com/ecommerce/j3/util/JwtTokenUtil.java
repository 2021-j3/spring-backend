package com.ecommerce.j3.util;

import com.ecommerce.j3.domain.J3UserDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@PropertySource("classpath:application.properties")
public class JwtTokenUtil {
    // 2021-03-08 penguin418: static 은 @value 를 사용하려면 setter 를 넣어야 하므로  immutability 를 고려하여 원상복구 했습니다
    private final static String SECRET_KEY="secret";
    public final static String ACCESS_TOKEN_NAME="ACCESS_TOKEN";
    public final static String REFRESH_TOKEN_NAME="REFRESH_TOKEN";
    public final static Long ACCESS_EXPIRATION_MS=864000L;
    public final static Long REFRESH_EXPIRATION_MS=86400000L;

    public String parseTokenString(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public String issueAccessToken(J3UserDetails userDetails){
        return issue(userDetails, ACCESS_EXPIRATION_MS);
    }

    public String issueRefreshToken(J3UserDetails userDetails){
        return issue(userDetails, REFRESH_EXPIRATION_MS);
    }

    // 자세한 정보는 https://jwt.io
    public String issue(J3UserDetails userDetails, Long expiration) {
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
                // 2020-02-27 penguin 만료시간 하루로 늘림
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료시간
                .compact();
    }

    /**
     * 이메일을 획득하는 메소드, filter 레이어에서는 사용 금지
     * @param token { String  } 토큰
     * @return
     */
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
                throw new SignatureException("Invalid JWT signature");
            } catch (MalformedJwtException e) {
                throw new MalformedJwtException("Invalid JWT token", e);
            } catch (ExpiredJwtException e) {
                throw new RuntimeException("Expired JWT token", e);
            } catch (UnsupportedJwtException e) {
                throw new UnsupportedJwtException("Unsupported JWT token", e);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("JWT claims string is empty.", e);
            }
        }
        return false;
    }
}
