package com.example.functionalBookstore.domain.user.application;

import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import com.example.functionalBookstore.domain.user.infrastructure.UserSpringSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserCommandController.class)
class UserCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSpringSecurityService securityService;

    @MockBean
    private AddNewUser addNewUser;

    @Test
    void shouldShowRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration")).andExpect(status().isOk());
    }
}