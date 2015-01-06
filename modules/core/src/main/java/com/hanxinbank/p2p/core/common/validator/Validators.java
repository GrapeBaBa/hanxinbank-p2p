package com.hanxinbank.p2p.core.common.validator;

import com.hanxinbank.p2p.core.user.validator.CaptchaValidator;
import com.hanxinbank.p2p.core.user.validator.PasswordValidator;
import com.hanxinbank.p2p.core.user.validator.TermsAndConditionsValidator;
import com.hanxinbank.p2p.core.user.validator.UsernameValidator;

import javax.servlet.http.HttpServletRequest;

public class Validators {

    public static Validator<String> newCaptchaValidator(HttpServletRequest request) {
        return new CaptchaValidator(request);
    }

    public static Validator<String> newPasswordValidator() {
        return new PasswordValidator();
    }

    public static Validator<String> newUsernameValidator() {
        return new UsernameValidator();
    }

    public static Validator<String> newTermsAndConditionsValidator() {
        return new TermsAndConditionsValidator();
    }
}
