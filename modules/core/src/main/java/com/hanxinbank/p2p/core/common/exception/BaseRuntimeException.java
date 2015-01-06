package com.hanxinbank.p2p.core.common.exception;

public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
