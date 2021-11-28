package com.example.functionalBookstore.domain.cart.application;

import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final GetLoggedUserCartItems getLoggedUserCartItems;

    @GetMapping("/shoppingCart")
    public String showCart(ModelMap model){
        model.addAttribute("cart", getLoggedUserCartItems.getLoggedUserCartItems());
        return "cart";
    }

}
