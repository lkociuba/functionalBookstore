package com.example.functionalBookstore.application;

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
@WebMvcTest(controllers = FunctionalBookstoreApplicationController.class)
class FunctionalBookstoreApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSpringSecurityService securityService;

    @Test
    void shouldShowWelcomePage() throws Exception {
        mockMvc.perform(get("/functionalBookstore")).andExpect(status().isOk());
    }
}