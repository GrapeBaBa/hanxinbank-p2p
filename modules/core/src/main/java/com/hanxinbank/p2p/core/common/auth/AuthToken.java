package com.hanxinbank.p2p.core.common.auth;

import com.hanxinbank.p2p.core.common.domain.BaseUser;
import com.hanxinbank.p2p.core.user.domain.User;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class AuthToken implements Serializable {
    private Optional<? extends BaseUser> userOptional;

    private LocalDateTime lastOperationAt;

    public static final AuthToken EMPTY_AUTH_TOKEN = new AuthToken(Optional.<User>empty(), LocalDateTime.from(Instant.EPOCH));

    public AuthToken(Optional<? extends BaseUser> userOptional, LocalDateTime lastOperationAt) {
        this.userOptional = userOptional;
        this.lastOperationAt = lastOperationAt;
    }

    public Optional<? extends BaseUser> getUserOptional() {
        return userOptional;
    }

    public boolean isLoggedIn(int cookieTimeoutMinutes) {
        return userOptional.isPresent() && !isTimeout(cookieTimeoutMinutes);
    }

    public Long getLastOperationTime() {
        return lastOperationAt.getLong(ChronoField.EPOCH_DAY);
    }

    public boolean causedByTimeout(int cookieTimeoutMinutes) {
        return userOptional.isPresent() && isTimeout(cookieTimeoutMinutes);
    }

    private boolean isTimeout(int cookieTimeoutMinutes) {
        return lastOperationAt.plusMinutes(cookieTimeoutMinutes).isBefore(LocalDateTime.now());
    }
}