package com.hanxinbank.p2p.core.admin.domain;

import com.hanxinbank.p2p.core.common.domain.BaseUser;
import com.hanxinbank.p2p.core.user.domain.Credential;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ADMINS")
public class Admin extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMINS")
    @SequenceGenerator(name = "SEQ_ADMINS", sequenceName = "SEQ_ADMINS", allocationSize = 1)
    private Long id;

    private Credential credential;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private AdminRole adminRole;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    public Admin() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public Credential getCredential() {
        return credential;
    }

    public AdminRole getAdminRole() {
        return adminRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }
}

