package com.hanxinbank.p2p.core.user.validator;

import com.google.common.base.Strings;
import com.hanxinbank.p2p.core.common.exception.ValidateException;
import com.hanxinbank.p2p.core.common.validator.Validator;

import static com.hanxinbank.p2p.core.common.exception.Error.INVALID_USERNAME;

public class UsernameValidator implements Validator<String> {
    @Override
    public void validate(String username) {
        if (Strings.isNullOrEmpty(username) || !username.matches("^[a-zA-Z_][a-zA-Z0-9_]{5,}$"))
            throw new ValidateException(INVALID_USERNAME);
    }
}
