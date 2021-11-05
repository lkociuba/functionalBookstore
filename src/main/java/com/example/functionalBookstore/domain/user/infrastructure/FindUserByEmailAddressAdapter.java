package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.FindUserByEmailAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserByEmailAddressAdapter implements FindUserByEmailAddress {

    private final UserRepository userRepository;

    @Override
    public User user(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}

