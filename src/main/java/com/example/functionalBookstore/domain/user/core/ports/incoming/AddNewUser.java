package com.example.functionalBookstore.domain.user.core.ports.incoming;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.model.User;

public interface AddNewUser {
    User save(AddUserCommand addUserCommand);
}
