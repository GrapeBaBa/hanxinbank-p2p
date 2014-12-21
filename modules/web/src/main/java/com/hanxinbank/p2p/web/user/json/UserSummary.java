package com.hanxinbank.p2p.web.user.json;

import com.hanxinbank.p2p.core.user.domain.User;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class UserSummary {
    private String birthday;

    private String name;

    public UserSummary(String birthday, String name) {
        this.birthday = birthday;
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public static Function<User, UserSummary> userToUserSummary = user -> new UserSummary(user.getUserIdentity().getBirthDay().format(DateTimeFormatter.ISO_LOCAL_DATE), user.getUserIdentity().getName());
}
