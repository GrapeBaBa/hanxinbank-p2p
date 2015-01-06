package com.hanxinbank.p2p.core.user.domain;

import com.hanxinbank.p2p.core.account.domain.Account;
import com.hanxinbank.p2p.core.common.domain.BaseUser;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1)
    private Long id;

    private Credential credential;

    @Column(name = "EMAIL")
    private String email;

    private UserIdentity userIdentity;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "IS_MOBILE_AUTHED")
    private Boolean isMobileAuthed;

    @Column(name = "IS_IDENTITY_AUTHED")
    private Boolean isIdentityAuthed;

    @Column(name = "IS_LOCKED")
    private Boolean isLocked;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Account> accounts;

    @Version
    private Long version;

    public User() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public Credential getCredential() {
        return credential;
    }

    public String getEmail() {
        return email;
    }

    public UserIdentity getUserIdentity() {
        return userIdentity;
    }

    public String getMobile() {
        return mobile;
    }


    public Boolean isLocked() {
        return isLocked;
    }

    public Long getVersion() {
        return version;
    }

    public Boolean isMobileAuthed() {
        return isMobileAuthed;
    }

    public Boolean isIdentityAuthed() {
        return isIdentityAuthed;
    }

    public static Builder aUser() {
        return new Builder();
    }

    public static class Builder {
        private Credential credential;

        private String email;

        private UserIdentity userIdentity;

        private String mobile;

        private Boolean isMobileAuthed;

        private Boolean isIdentityAuthed;

        private Boolean isLocked;

        public Builder withCredential(Credential credential) {
            this.credential = credential;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withUserIdentity(UserIdentity userIdentity) {
            this.userIdentity = userIdentity;
            return this;
        }

        public Builder withMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder withIsMobileAuthed(Boolean isMobileAuthed) {
            this.isMobileAuthed = isMobileAuthed;
            return this;
        }

        public Builder withIsIdentityAuthed(Boolean isIdentityAuthed) {
            this.isIdentityAuthed = isIdentityAuthed;
            return this;
        }

        public Builder withIsLocked(Boolean isLocked) {
            this.isLocked = isLocked;
            return this;
        }

        public User build() {
            User user = new User();
            user.credential = credential;
            user.email = email;
            user.isIdentityAuthed = isIdentityAuthed;
            user.isLocked = isLocked;
            user.isMobileAuthed = isMobileAuthed;
            user.mobile = mobile;
            user.userIdentity = userIdentity;
            return user;
        }
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

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
