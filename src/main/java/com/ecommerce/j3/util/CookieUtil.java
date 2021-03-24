package com.ecommerce.j3.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addCookie(HttpServletResponse res, String name, String value, Long expireAt){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Math.toIntExact(expireAt));
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse res, String name){
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest req, String name){
        final Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for(Cookie cookie : cookies){
                if (cookie.getName().equals(name))
                    return cookie;
            }
        return null;
    }
}
