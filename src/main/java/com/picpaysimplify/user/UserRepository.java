package com.picpaysimplify.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
}
