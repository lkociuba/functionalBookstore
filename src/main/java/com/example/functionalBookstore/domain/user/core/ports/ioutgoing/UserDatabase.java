package com.example.functionalBookstore.domain.user.core.ports.ioutgoing;

import com.example.functionalBookstore.domain.user.core.model.User;

public interface UserDatabase {
    User save(User user);

    User findByEmail(String email);
}
