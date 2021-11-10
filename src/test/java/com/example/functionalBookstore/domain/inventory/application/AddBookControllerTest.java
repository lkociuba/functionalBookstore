package com.example.functionalBookstore.domain.inventory.application;

import com.example.functionalBookstore.domain.inventory.core.ports.incoming.AddNewBook;
import com.example.functionalBookstore.domain.user.infrastructure.UserSpringSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AddBookController.class)
class AddBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddNewBook addNewBook;

    @MockBean
    private UserSpringSecurityService securityService;


    @Test
    @WithMockUser(username = "manager@email", roles = {"USER", "EMPLOYEE"})
    void shouldShowBookListPage() throws Exception {
        mockMvc.perform(get("/addBook")).andExpect(status().isOk());
    }
}