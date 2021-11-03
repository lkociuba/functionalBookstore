package com.example.functionalBookstore.infrastructure;

import com.example.functionalBookstore.domain.user.core.UserService;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.core.ports.ioutgoing.UserDatabase;
import com.example.functionalBookstore.domain.user.infrastructure.UserDatabaseAdapter;
import com.example.functionalBookstore.domain.user.infrastructure.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserDomainConfig {

    @Bean
    public UserDatabase userDatabase(UserRepository userRepository){
        return new UserDatabaseAdapter(userRepository);
    }

    @Bean
    public AddNewUser addNewUser(UserDatabase userDatabase, BCryptPasswordEncoder passwordEncoder){
        return new UserService(userDatabase,passwordEncoder);
    }
}
