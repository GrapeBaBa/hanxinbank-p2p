package com.hanxinbank.p2p.core.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserIdentity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTITY_NUMBER")
    private String number;

    public UserIdentity() {

    }

    public String getName() {
        return name;
    }

    public UserIdentity(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserIdentity that = (UserIdentity) o;

        return Objects.equals(this.number, that.number)
                && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.name);
    }
}

