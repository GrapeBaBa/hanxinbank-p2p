package com.hanxinbank.p2p.core.user.validator;

import com.hanxinbank.p2p.core.common.exception.*;
import com.hanxinbank.p2p.core.common.exception.Error;
import com.hanxinbank.p2p.core.common.validator.Validator;

public class TermsAndConditionsValidator implements Validator<String> {
    @Override
    public void validate(String s) {
        if (!"accept".equals(s)) {
            throw new ValidateException(Error.NOT_ACCEPTED_TERMS_AND_CONDITIONS);
        }
    }
}
