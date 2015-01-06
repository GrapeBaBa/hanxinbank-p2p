package com.hanxinbank.p2p.core.user.service;

import com.hanxinbank.p2p.core.account.domain.Account;
import com.hanxinbank.p2p.core.account.domain.AccountType;
import com.hanxinbank.p2p.core.common.domain.RMB;
import com.hanxinbank.p2p.core.user.domain.Credential;
import com.hanxinbank.p2p.core.user.domain.User;
import com.hanxinbank.p2p.core.user.json.RegisterRequest;
import com.hanxinbank.p2p.core.user.repository.UserRepository;
import com.hanxinbank.p2p.core.user.validator.MobileIdentityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MobileIdentityValidator mobileIdentityValidator;

    @Transactional
    public User register(RegisterRequest registerRequest) {
        mobileIdentityValidator.validate(registerRequest.getMobile());

        User user = User.aUser()
                .withCredential(new Credential(registerRequest.getUsername(), registerRequest.getPassword()))
                .withMobile(registerRequest.getMobile())
                .build();
        user.setAccounts(Arrays.asList(Account.aAccount()
                        .withAccountType(AccountType.POOL)
                        .withBalance(RMB.zero())
                        .withFrozenFund(RMB.zero())
                        .withUser(user)
                        .build(),
                Account.aAccount()
                        .withAccountType(AccountType.BONUS)
                        .withBalance(RMB.zero())
                        .withFrozenFund(RMB.zero())
                        .withUser(user)
                        .build()));
        return userRepository.save(user);
    }
}
