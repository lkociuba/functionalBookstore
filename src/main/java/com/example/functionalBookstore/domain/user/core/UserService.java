package com.example.functionalBookstore.domain.user.core;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.EmailAddress;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
public class UserService implements AddNewUser {

    private final UserDatabase userDatabase;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(AddUserCommand addUserCommand) {
        User user = new User(
                addUserCommand.getFirstName(),
                addUserCommand.getLastName(),
                new EmailAddress(addUserCommand.getEmail()),
                passwordEncoder.encode(addUserCommand.getPassword()),
                new HashSet<Role>(List.of(new Role("ROLE_USER")))
        );
        return userDatabase.save(user);
    }
}
