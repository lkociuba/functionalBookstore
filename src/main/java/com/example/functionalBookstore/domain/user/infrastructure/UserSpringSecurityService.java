package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserSpringSecurityService implements UserDetailsService {

    private final UserDatabase userDatabase;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
