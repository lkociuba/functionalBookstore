package com.example.functionalBookstore.domain.cart.application;

import com.example.functionalBookstore.domain.cart.core.CartService;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import com.example.functionalBookstore.domain.user.infrastructure.UserSpringSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private GetLoggedUser getLoggedUser;

    @MockBean
    private UserSpringSecurityService securityService;

    @Test
    @WithMockUser(username = "manager@email")
    void shouldShowCartPage() throws Exception {
        mockMvc.perform(get("/shoppingCart")).andExpect(status().isOk());
    }
}