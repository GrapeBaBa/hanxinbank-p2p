package com.hanxinbank.p2p.core.account.domain;

import com.hanxinbank.p2p.core.common.domain.RMB;
import com.hanxinbank.p2p.core.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNTS")
    @SequenceGenerator(name = "SEQ_ACCOUNTS", sequenceName = "SEQ_ACCOUNTS", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "BALANCE_AMOUNT")
    private RMB balance;

    @Column(name = "FROZEN_AMOUNT")
    private RMB frozenFund;

    @Column(name = "DEAL_PASSWORD")
    private String dealPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private AccountType accountType;

    public Account() {
    }

    public static Builder aAccount() {
        return new Builder();
    }

    public static class Builder {
        private User user;

        private RMB balance;

        private RMB frozenFund;

        private String dealPassword;

        private AccountType accountType;

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withBalance(RMB balance) {
            this.balance = balance;
            return this;
        }

        public Builder withFrozenFund(RMB frozenFund) {
            this.frozenFund = frozenFund;
            return this;
        }

        public Builder withDealPassword(String dealPassword) {
            this.dealPassword = dealPassword;
            return this;
        }

        public Builder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.accountType = accountType;
            account.balance = balance;
            account.dealPassword = dealPassword;
            account.frozenFund = frozenFund;
            account.user = user;
            return account;
        }
    }
}
