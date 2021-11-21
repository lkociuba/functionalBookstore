package com.example.functionalBookstore.domain.user.core;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDatabase userDatabaseMock;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldAddNewUser() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );

        AddUserCommand addUserCommand = AddUserCommand.builder()
                .firstName("John")
                .lastName("Wick")
                .email("john@email.com")
                .password("password")
                .build();

        given(userDatabaseMock.save(Mockito.any())).willReturn(user);

        //when
        User result = userService.save(addUserCommand);

        //then
        assertThat(result, is(user));
    }

    @Test
    void shouldThrowExceptionFromNullAddUserCommand() {
        assertThrows(NullPointerException.class, () ->
                userService.save(null));
    }

    @Test
    void shouldGetLoggedUser() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );

        given(userDatabaseMock.getLoggedUserEmail()).willReturn("manager@email");

        given(userDatabaseMock.findByEmail("manager@email")).willReturn(Optional.of(user));

        //when
        Optional<User> result = userService.getLoggedUser();

        //then
        assertThat(result, is(Optional.of(user)));
    }

    @Test
    void shouldGetEmptyOptionalFromNullLoggedUserEmail() {
        //given
        given(userDatabaseMock.getLoggedUserEmail()).willReturn(null);

        //when
        Optional<User> result = userService.getLoggedUser();

        //then
        assertThat(result, is(Optional.empty()));
    }

    @Test
    void name() {
        //given
        given(userDatabaseMock.getLoggedUserEmail()).willReturn("manager@email");

        given(userDatabaseMock.findByEmail("manager@email")).willReturn(Optional.empty());

        //when
        Optional<User> result = userService.getLoggedUser();

        //then
        assertThat(result, is(Optional.empty()));

    }
}