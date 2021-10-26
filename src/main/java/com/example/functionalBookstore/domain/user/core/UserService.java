package com.example.functionalBookstore.domain.user.core;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;

@AllArgsConstructor
public class UserService implements AddNewUser {

    private final UserDatabase userDatabase;

    @Override
    public User save(AddUserCommand addUserCommand) {
        User user = new User(
                addUserCommand.getFirstName(),
                addUserCommand.getLastName(),
                addUserCommand.getEmail(),
                addUserCommand.getPassword(),
                new HashSet<Role>(Arrays.asList(new Role("ROLE_USER")))
        );
        return userDatabase.save(user);
    }
}
