package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserDatabase {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(
                    userRepository.findByEmail(email));
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public String getLoggedUserEmail() {
        var principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}
