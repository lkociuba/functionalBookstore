package com.example.functionalBookstore.domain.user.core.ports.outgoing;

import com.example.functionalBookstore.domain.user.core.model.User;

import java.util.Optional;

public interface UserDatabase {
    User save(User user);

    Optional<User> findByEmail(String email);

    String getLoggedUserEmail();
}
