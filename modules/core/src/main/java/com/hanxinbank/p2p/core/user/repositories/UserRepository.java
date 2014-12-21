package com.hanxinbank.p2p.core.user.repositories;

import com.hanxinbank.p2p.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
