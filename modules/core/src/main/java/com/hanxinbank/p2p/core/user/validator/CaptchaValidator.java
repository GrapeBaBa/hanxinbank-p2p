package com.hanxinbank.p2p.core.user.validator;

import com.hanxinbank.p2p.core.common.exception.ValidateException;
import com.hanxinbank.p2p.core.common.validator.Validator;
import com.hanxinbank.p2p.core.crypto.HmacSHA256;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.hanxinbank.p2p.core.common.exception.Error.INVALID_CAPTCHA;
import static java.util.Optional.ofNullable;

public class CaptchaValidator implements Validator<String> {
    public static final String CAPTCHA_KEY = "X-Captcha-Text";

    @Value("{auth.secretkey}")
    private String secretKey;

    private final HttpServletRequest request;

    public CaptchaValidator(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void validate(String s) {
        Optional<Cookie[]> cookiesOptional = ofNullable(request.getCookies());
        if (!cookiesOptional.isPresent()) {
            throw new ValidateException(INVALID_CAPTCHA);
        }

        Boolean hasCaptchaText = false;
        for (Cookie cookie : cookiesOptional.get()) {
            if (CAPTCHA_KEY.equals(cookie.getName())) {
                hasCaptchaText = true;
                isCaptchaTextValid(cookie.getValue(), s);
            }
        }

        if (!hasCaptchaText) {
            throw new ValidateException(INVALID_CAPTCHA);
        }
    }

    private void isCaptchaTextValid(String encryptedCaptchaText, String submittedCaptchaText) {
        if (!HmacSHA256.encrypt(submittedCaptchaText + secretKey, secretKey).equals(encryptedCaptchaText)) {
            throw new ValidateException(INVALID_CAPTCHA);
        }
    }

}
