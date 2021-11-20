package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.GetLoggedUser;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@RequiredArgsConstructor
public class GetLoggedUserAdapter implements GetLoggedUser {

    private final UserDatabase userDatabase;

    @Override
    public Optional<User> getLoggedUser() {
        var loggedUserName = getLoggedUserName();
        return userDatabase.findByEmail(loggedUserName);
    }

    private String getLoggedUserName() {
        var principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}
