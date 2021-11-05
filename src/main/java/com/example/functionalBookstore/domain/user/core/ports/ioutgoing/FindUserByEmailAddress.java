package com.example.functionalBookstore.domain.user.core.ports.ioutgoing;

import com.example.functionalBookstore.domain.user.core.model.User;

public interface FindUserByEmailAddress {
    User user(String emailAddress);
}
