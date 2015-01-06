package com.hanxinbank.p2p.core.crypto;

import com.hanxinbank.p2p.core.common.exception.BaseRuntimeException;

public class CryptoException extends BaseRuntimeException {

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
