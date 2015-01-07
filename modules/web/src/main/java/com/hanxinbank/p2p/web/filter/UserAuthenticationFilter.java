package com.hanxinbank.p2p.web.filter;

import com.hanxinbank.p2p.core.common.filter.AuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationFilter extends AuthenticationFilter {
    public UserAuthenticationFilter() {
        this.ignore = new String[]{
                "/api/captcha$"
        };
    }
}
