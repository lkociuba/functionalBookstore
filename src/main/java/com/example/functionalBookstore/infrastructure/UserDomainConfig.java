package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.user.core.UserService;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import com.example.functionalBookstore.domain.user.infrastructure.UserDatabaseAdapter;
import com.example.functionalBookstore.domain.user.infrastructure.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDomainConfig {

    @Bean
    public UserDatabase userDatabase(UserRepository userRepository){
        return new UserDatabaseAdapter(userRepository);
    }

    @Bean
    public AddNewUser addNewUser(UserDatabase userDatabase){
        return new UserService(userDatabase);
    }
}
