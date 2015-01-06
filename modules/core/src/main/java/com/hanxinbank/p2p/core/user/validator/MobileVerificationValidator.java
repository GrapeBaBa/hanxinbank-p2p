package com.hanxinbank.p2p.core.user.validator;

import com.google.common.base.Splitter;
import com.hanxinbank.p2p.core.common.exception.ValidateException;
import com.hanxinbank.p2p.core.common.validator.Validator;
import com.hanxinbank.p2p.core.crypto.HmacSHA256;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static com.hanxinbank.p2p.core.common.exception.Error.INVALID_CAPTCHA;
import static com.hanxinbank.p2p.core.common.exception.Error.INVALID_MOBILE_VERIFY_CODE;
import static java.util.Optional.ofNullable;

@Component
public class MobileVerificationValidator implements Validator<String> {
    public static final String MOBILE_VERIFICATION_PREFIX = "X-Mobile-Verification";

    private final static int MOBILE_VERIFICATION_ENCRYPTED_MESSAGE_INDEX = 0;

    private final static int MOBILE_VERIFICATION_GENERATED_TIME_INDEX = 1;

    @Value("{auth.secretkey}")
    private String secretKey;

    @Value("{mobile.verification.timeout}")
    private String mobileVerificationTimeout;

    private HttpServletRequest request;

    private String mobile;

    public MobileVerificationValidator setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public MobileVerificationValidator setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public void validate(String s) {
        Optional<Cookie[]> cookiesOptional = ofNullable(request.getCookies());
        if (!cookiesOptional.isPresent()) {
            throw new ValidateException(INVALID_MOBILE_VERIFY_CODE);
        }

        Boolean hasMobileVerification = false;
        for (Cookie cookie : cookiesOptional.get()) {
            if ((MOBILE_VERIFICATION_PREFIX + "-" + mobile).equals(cookie.getName())) {
                hasMobileVerification = true;
                isMobileVerificationValid(cookie.getValue(), s);
            }
        }

        if (!hasMobileVerification) {
            throw new ValidateException(INVALID_CAPTCHA);
        }
    }

    private void isMobileVerificationValid(String encodedText, String submittedMobileVerification) {
        List<String> cookieContents = Splitter.on(":").splitToList(new String(Base64.decodeBase64(encodedText), StandardCharsets.UTF_8));
        String encryptedMobileVerification = cookieContents.get(MOBILE_VERIFICATION_ENCRYPTED_MESSAGE_INDEX);
        String generatedTime = cookieContents.get(MOBILE_VERIFICATION_GENERATED_TIME_INDEX);

        validateMobileVerificationTimeout(generatedTime);
        validateMobileVerificationIdentity(encryptedMobileVerification, submittedMobileVerification, generatedTime);
    }

    private void validateMobileVerificationTimeout(String generatedTimeText) {
        LocalDateTime generatedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(generatedTimeText)), ZoneId.systemDefault());
        if (generatedTime.plusSeconds(Long.valueOf(mobileVerificationTimeout)).isBefore(LocalDateTime.now())) {
            throw new ValidateException(INVALID_MOBILE_VERIFY_CODE);
        }
    }

    private void validateMobileVerificationIdentity(String encryptedMobileVerification, String submittedMobileVerification, String generatedTime) {
        if (!HmacSHA256.encrypt(submittedMobileVerification + secretKey + generatedTime, secretKey).equals(encryptedMobileVerification)) {
            throw new ValidateException(INVALID_MOBILE_VERIFY_CODE);
        }
    }
}
