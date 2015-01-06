package com.hanxinbank.p2p.core.user.validator;

import com.google.common.base.Strings;
import com.hanxinbank.p2p.core.common.exception.*;
import com.hanxinbank.p2p.core.common.exception.Error;
import com.hanxinbank.p2p.core.common.validator.Validator;
import com.hanxinbank.p2p.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MobileIdentityValidator implements Validator<String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(String s) {
        if (Strings.isNullOrEmpty(s)) {
            throw new ValidateException(Error.EMPTY_MOBILE);
        }

        if (!userRepository.findByMobile(s).isEmpty()) {
            throw new ValidateException(Error.DUPLICATED_MOBILE);
        }
    }
}
