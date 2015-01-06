package com.hanxinbank.p2p.web.captcha;

import com.hanxinbank.p2p.core.common.exception.BaseRuntimeException;

public class CaptchaGenerationException extends BaseRuntimeException {
    public CaptchaGenerationException(String message) {
        super(message);
    }
}
