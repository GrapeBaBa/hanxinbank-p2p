package com.hanxinbank.p2p.core.common.auth;

import com.google.common.base.Splitter;
import com.hanxinbank.p2p.core.common.Cookies;
import com.hanxinbank.p2p.core.common.domain.BaseUser;
import com.hanxinbank.p2p.core.crypto.HmacSHA256;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static com.hanxinbank.p2p.core.common.Cookies.*;
import static java.util.Optional.ofNullable;

@Component
public class AuthHandler<T extends CrudRepository<? extends BaseUser, Long>> {

    public final static int TOKEN_OPERATION_TIME_INDEX = 2;

    public final static String AUTH_TOKEN = "X-AuthToken";

    public static final String LOGON_USER_ID = "X-Logon-UserId";

    public static final String LOGON_USER = "X-Logon-User";

    private final static Logger logger = LoggerFactory.getLogger(AuthHandler.class);

    private final static int TOKEN_ENCRYPTED_MESSAGE_INDEX = 0;

    private final static int TOKEN_USER_ID_INDEX = 1;

    @Autowired
    private T userRepository;

    @Value("{auth.secretkey}")
    private String secretKey;

    public Cookie toCookie(AuthToken authToken, String path) {
        long userId = authToken.getUserOptional().map(BaseUser::getId).orElse(-1l);
        String encryptedText = HmacSHA256.encrypt(userId + secretKey + authToken.getLastOperationTime(), secretKey);
        String token = Base64.encodeBase64String((encryptedText + ":" + userId + ":" + authToken.getLastOperationTime()).getBytes(StandardCharsets.UTF_8));

        return Cookies.toCookie(AUTH_TOKEN, token, ofNullable(path));
    }

    public AuthToken toAuthToken(HttpServletRequest request) {
        Optional<Cookie[]> cookiesOptional = ofNullable(request.getCookies());
        if (!cookiesOptional.isPresent()) {
            return AuthToken.EMPTY_AUTH_TOKEN;
        }

        for (Cookie cookie : cookiesOptional.get()) {
            if (AUTH_TOKEN.equals(cookie.getName())) {
                return buildAuthToken(cookie);
            }
        }

        return AuthToken.EMPTY_AUTH_TOKEN;
    }

    public <U extends BaseUser> Cookie toCookie(U user) {
        return toCookie(user, null);
    }

    public <U extends BaseUser> Cookie toCookie(U user, String path) {
        return toCookie(new AuthToken(ofNullable(user), LocalDateTime.now()), path);
    }

    public Cookie toExpiredCookie(HttpServletRequest request) {
        return Cookies.toExpiredCookie(AuthHandler.AUTH_TOKEN, ofNullable(request.getContextPath()));
    }

    private AuthToken buildAuthToken(Cookie cookie) {
        List<String> cookieContents = Splitter.on(":").splitToList(new String(Base64.decodeBase64(cookie.getValue()), StandardCharsets.UTF_8));
        if (cookieContents.size() < 3) {
            return AuthToken.EMPTY_AUTH_TOKEN;
        }

        String userId = cookieContents.get(TOKEN_USER_ID_INDEX);
        String lastOperationTime = cookieContents.get(TOKEN_OPERATION_TIME_INDEX);
        if (!isTokenValid(cookieContents, userId, lastOperationTime)) {
            return AuthToken.EMPTY_AUTH_TOKEN;
        }

        try {
            return new AuthToken(ofNullable(userRepository.findOne(Long.valueOf(userId))), LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(lastOperationTime)), ZoneId.systemDefault()));
        } catch (Exception e) {
            logger.error("Build auth token failed, can not load user by id {}", userId);
            return AuthToken.EMPTY_AUTH_TOKEN;
        }
    }

    private boolean isTokenValid(List<String> cookieContents, String userId, String lastOperationTime) {
        return HmacSHA256.encrypt(userId + secretKey + lastOperationTime, secretKey).equals(cookieContents.get(TOKEN_ENCRYPTED_MESSAGE_INDEX));
    }
}
