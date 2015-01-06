package com.hanxinbank.p2p.core.user.repository;

import com.google.common.collect.ImmutableList;
import com.hanxinbank.p2p.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    ImmutableList<User> findByMobile(String mobile);
}
