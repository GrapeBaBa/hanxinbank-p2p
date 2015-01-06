package com.hanxinbank.p2p.core.common;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class Cookies {
    public static Cookie toCookie(String name, String value, Optional<String> pathOpt) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath(pathOpt.orElse("/"));

        return cookie;
    }

    public static Cookie toExpiredCookie(String name, Optional<String> pathOpt) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath(pathOpt.orElse("/"));
        cookie.setHttpOnly(true);

        return cookie;
    }
}
