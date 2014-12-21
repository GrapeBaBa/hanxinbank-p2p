package com.hanxinbank.p2p.core.user.service;

import com.hanxinbank.p2p.core.user.domain.Credential;
import com.hanxinbank.p2p.core.user.domain.Identity;
import com.hanxinbank.p2p.core.user.domain.User;
import com.hanxinbank.p2p.core.user.domain.UserIdentity;
import com.hanxinbank.p2p.core.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User test() {
        User user = new User(new Credential("grapebaba", "123456"),
                "281165273@qq.com",
                new UserIdentity("葡萄", UserIdentity.Gender.FEMALE, new Identity(Identity.Type.IDENTITY_CARD, "123456789"), LocalDate.now()),
                User.UserType.CUSTOMER,
                "13388888888",
                User.AuthenticationStatus.NOT_AUTHENTICATED);
        user = userRepository.save(user);
        return userRepository.findOne(user.getId());
    }
}
