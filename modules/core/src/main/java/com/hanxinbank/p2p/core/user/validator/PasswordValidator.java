package com.hanxinbank.p2p.core.user.validator;

import com.google.common.base.Strings;
import com.hanxinbank.p2p.core.common.exception.ValidateException;
import com.hanxinbank.p2p.core.common.validator.Validator;

import static com.hanxinbank.p2p.core.common.exception.Error.INVALID_PASSWORD;

public class PasswordValidator implements Validator<String> {
    @Override
    public void validate(String s) {
        if (Strings.isNullOrEmpty(s) || s.length() < 8 || s.matches("[0-9]+"))
            throw new ValidateException(INVALID_PASSWORD);
    }
}
