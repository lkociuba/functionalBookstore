package com.example.functionalBookstore.domain.user.core;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.EmailAddress;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDatabase userDatabase;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldAddNewUser() {
        //given
        User user = new User(
                "John",
                "Wick",
                new EmailAddress("john@email.com"),
                "password",
                new HashSet<Role>(List.of(new Role("ROLE_USER")))
        );

        AddUserCommand addUserCommand = AddUserCommand.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john@email.com")
                .password("passeord")
                .build();

        given(userDatabase.save(Mockito.any())).willReturn(user);

        //when
        User result = userService.save(addUserCommand);

        //then
        assertThat(result, is(user));
    }
}