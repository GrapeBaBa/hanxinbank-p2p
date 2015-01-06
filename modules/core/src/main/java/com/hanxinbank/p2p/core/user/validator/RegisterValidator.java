package com.hanxinbank.p2p.core.user.validator;

import com.hanxinbank.p2p.core.common.validator.Validator;
import com.hanxinbank.p2p.core.user.json.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.hanxinbank.p2p.core.common.validator.Validators.*;

@Component
public class RegisterValidator implements Validator<RegisterRequest> {

    @Autowired
    private MobileVerificationValidator mobileVerificationValidator;

    private HttpServletRequest request;

    public RegisterValidator setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public void validate(RegisterRequest registerRequest) {
        newUsernameValidator().validate(registerRequest.getUsername());
        newPasswordValidator().validate(registerRequest.getPassword());
        newCaptchaValidator(request).validate(registerRequest.getCaptcha());
        newTermsAndConditionsValidator().validate(registerRequest.getTermsAndConditions());
        mobileVerificationValidator
                .setRequest(request)
                .setMobile(registerRequest.getMobile())
                .validate(registerRequest.getMobileVerification());
    }
}
