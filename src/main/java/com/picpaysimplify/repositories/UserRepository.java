package com.picpaysimplify.repositories;

import com.picpaysimplify.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUserId(Long user_id);
    Optional<Users> findUserByEmail(String email);
}
