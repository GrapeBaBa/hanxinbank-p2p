package com.hanxinbank.p2p.core.user.json;

import com.hanxinbank.p2p.core.user.domain.User;

import java.util.function.Function;

public class UserSummary {

    private String name;

    public UserSummary(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public static Function<User, UserSummary> userToUserSummary = user -> new UserSummary(user.getUserIdentity().getName());
}
