package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserDatabaseAdapterTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserDatabaseAdapter userDatabaseAdapter;

    @Test
    void shouldReturnSavedUserAfterAddNewUserToDatabase() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );

        given(userRepositoryMock.save(Mockito.any(User.class))).willReturn(user);

        //when
        User result = userDatabaseAdapter.save(new User());

        //then
        assertThat(result, is(user));
    }

    @Test
    void shouldFindUserByEmail() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<>(List.of(new Role("ROLE_USER")))
        );

        given(userRepositoryMock.findByEmail(Mockito.anyString())).willReturn(user);

        //when
        Optional<User> result = userDatabaseAdapter.findByEmail(Mockito.anyString());

        //then
        assertThat(result, is(Optional.of(user)));
    }

    @Test
    void shouldThrowExceptionFromEmptyEmail() {
        //given
        given(userRepositoryMock.findByEmail(Mockito.anyString())).willReturn(null);

        //then
        assertThrows(Exception.class, () ->
                userDatabaseAdapter.findByEmail(null));
    }
}