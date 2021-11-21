package com.example.functionalBookstore.domain.user.core;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import com.example.functionalBookstore.domain.user.core.ports.outgoing.UserDatabase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserService implements AddNewUser, GetLoggedUser {

    private final UserDatabase userDatabase;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(AddUserCommand addUserCommand) {
        User user = new User(
                addUserCommand.getFirstName(),
                addUserCommand.getLastName(),
                addUserCommand.getEmail(),
                passwordEncoder.encode(addUserCommand.getPassword()),
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );
        return userDatabase.save(user);
    }

    @Override
    public Optional<User> getLoggedUser() {
        var loggedUserEmail = userDatabase.getLoggedUserEmail();
        return userDatabase.findByEmail(loggedUserEmail);
    }
}
