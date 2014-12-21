package com.hanxinbank.p2p.core.user.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User {
    public static enum UserType {
        CUSTOMER
    }

    public static enum AuthenticationStatus {
        NOT_AUTHENTICATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1)
    private long id;

    private Credential credential;

    @Column(name = "EMAIL")
    private String Email;

    private UserIdentity userIdentity;

    @Column(name = "USER_TYPE")
    private UserType userType;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "AUTHENTICATION_STATUS")
    private AuthenticationStatus authenticateStatus;

    public User() {
    }

    public User(Credential credential, String email, UserIdentity userIdentity, UserType userType, String mobile, AuthenticationStatus authenticateStatus) {
        this.credential = credential;
        Email = email;
        this.userIdentity = userIdentity;
        this.userType = userType;
        this.mobile = mobile;
        this.authenticateStatus = authenticateStatus;
    }

    public long getId() {
        return id;
    }

    public Credential getCredential() {
        return credential;
    }

    public String getEmail() {
        return Email;
    }

    public UserIdentity getUserIdentity() {
        return userIdentity;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getMobile() {
        return mobile;
    }

    public AuthenticationStatus getAuthenticateStatus() {
        return authenticateStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        return Objects.equals(this.credential.getUsername(), that.credential.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.credential.getUsername());
    }
}
