package com.hanxinbank.p2p.core.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class Identity {
    public enum Type {
        IDENTITY_CARD, PASSPORT
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "IDENTITY_TYPE")
    private Type type;

    @Column(name = "IDENTITY_NUMBER")
    private String number;

    public Identity(Type type, String number) {
        this.type = type;
        this.number = number;
    }

    public Identity() {
    }

    public Type getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Identity that = (Identity) obj;
        return Objects.equals(this.type, that.type)
                && Objects.equals(this.number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.number);
    }
}
