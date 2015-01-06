package com.hanxinbank.p2p.core.common.exception;

public class ValidateException extends BaseRuntimeException {
    public ValidateException(Error error) {
        super(error.name());
    }
}
