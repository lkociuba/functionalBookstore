package com.example.functionalBookstore.domain.cart.application;

import com.example.functionalBookstore.domain.cart.core.ports.incoming.CalculateCartItemPrice;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final GetLoggedUserCartItems getLoggedUserCartItems;
    private final CalculateCartItemPrice calculateCartItemPrice;

    @GetMapping("/shoppingCart")
    public String showCart(ModelMap model){
        model.addAttribute("cart", getLoggedUserCartItems.getLoggedUserCartItems());
        model.addAttribute("calculatedPrice", calculateCartItemPrice.calculatePrice());
        return "cart";
    }

}
