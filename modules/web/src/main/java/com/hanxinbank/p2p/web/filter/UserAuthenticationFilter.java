package com.hanxinbank.p2p.web.filter;

import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationFilter extends com.hanxinbank.p2p.core.common.filter.AuthenticationFilter {
    public UserAuthenticationFilter() {
        this.ignore = new String[]{
                "/api/captcha$"
        };
    }
}
