package com.hanxinbank.p2p.core.user.domain;

import com.hanxinbank.p2p.core.jpa.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class UserIdentity {

    public static enum Gender {
        MALE, FEMALE
    }

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "BIRTHDAY")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate birthDay;

    private Identity identity;

    public UserIdentity() {

    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Identity getIdentity() {
        return identity;
    }

    public UserIdentity(String name, Gender gender, Identity identity, LocalDate birthDay) {
        this.name = name;
        this.gender = gender;
        this.identity = identity;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserIdentity that = (UserIdentity) o;

        return Objects.equals(this.birthDay, that.birthDay)
                && Objects.equals(this.gender, that.gender)
                && Objects.equals(this.identity, that.identity)
                && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.birthDay, this.gender, this.identity, this.name);
    }
}

