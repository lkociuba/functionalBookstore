package com.example.functionalBookstore.domain.user.infrastructure;

import com.example.functionalBookstore.domain.user.core.model.Role;
import com.example.functionalBookstore.domain.user.core.model.User;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserSpringSecurityServiceTest {

    @Mock
    private UserDatabase userDatabaseMock;

    @InjectMocks
    private UserSpringSecurityService securityService;

    @Test
    void shouldSecurityLoginOK() {
        //given
        User user = new User(
                "John",
                "Wick",
                "john@email.com",
                "password",
                new HashSet<Role>(List.of(new Role("ROLE_USER")))
        );

        given(userDatabaseMock.findByEmail(Mockito.anyString())).willReturn(user);

        //when
        UserDetails loadedUser = securityService.loadUserByUsername(Mockito.anyString());

        //then
        assertThat(loadedUser, notNullValue());
    }

    @Test
    void shouldSecurityLoginThrowExceptionFromNullUser() {
        //given
        given(userDatabaseMock.findByEmail(Mockito.anyString())).willReturn(null);

        //when, then
        assertThrows(Exception.class, () ->
                securityService.loadUserByUsername(Mockito.anyString()));
    }

    @Test
    void shouldSecurityLoginThrowExceptionFronnNullEmail() {
        assertThrows(Exception.class, () ->
                securityService.loadUserByUsername(null));
    }
}