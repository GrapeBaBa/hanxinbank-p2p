package com.hanxinbank.p2p.core.common.filter;

import com.hanxinbank.p2p.core.common.auth.AuthHandler;
import com.hanxinbank.p2p.core.common.auth.AuthToken;
import com.hanxinbank.p2p.core.common.domain.BaseUser;
import com.hanxinbank.p2p.core.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hanxinbank.p2p.core.common.auth.AuthHandler.*;

public abstract class AuthenticationFilter extends IgnorableFilter {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private AuthHandler authHandler;

    @Value("${cookie.timeout.minutes}")
    private int cookieTimeoutMinutes;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (isIgnored(httpServletRequest)) {
            logger.debug("Ignore request by userAuthenticationFilter");
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        logger.debug("Not ignore request by userAuthenticationFilter");

        AuthToken authToken = authHandler.toAuthToken(httpServletRequest);
        Boolean loggedIn = authToken.isLoggedIn(cookieTimeoutMinutes);

        if (!loggedIn) {
            unauthenticated(httpServletResponse);
            return;
        }

        if (isPlainUser(authToken) && ((User) authToken.getUserOptional().get()).isLocked()) {
            httpServletResponse.addCookie(authHandler.toExpiredCookie(httpServletRequest));
            unauthenticated(httpServletResponse);
            return;
        } else {
            authenticated(httpServletRequest, httpServletResponse, authToken);
        }

        chain.doFilter(request, response);
    }

    private void unauthenticated(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.flushBuffer();
    }

    private void authenticated(HttpServletRequest request, HttpServletResponse response, AuthToken authToken) {
        request.setAttribute(LOGON_USER_ID, authToken.getUserOptional().map(BaseUser::getId));
        request.setAttribute(LOGON_USER, authToken.getUserOptional().get());
        response.addCookie(authHandler.toCookie(authToken.getUserOptional().get(), request.getContextPath()));
    }

    private boolean isPlainUser(AuthToken authToken) {
        return authToken.getUserOptional().get() instanceof User;
    }
}
