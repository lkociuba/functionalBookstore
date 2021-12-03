package com.example.functionalBookstore.domain.cart.application;

import com.example.functionalBookstore.domain.cart.core.ports.incoming.AddCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.CalculateCartItemPrice;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.DeleteCartItem;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.GetLoggedUserCartItems;
import com.example.functionalBookstore.domain.user.core.ports.incoming.GetLoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final GetLoggedUserCartItems getLoggedUserCartItems;
    private final CalculateCartItemPrice calculateCartItemPrice;
    private final GetLoggedUser getLoggedUser;
    private final AddCartItem addCartItem;
    private final DeleteCartItem deleteCartItem;

    @GetMapping("/shoppingCart")
    public String showCart(ModelMap model) {
        model.addAttribute("cart", getLoggedUserCartItems.getLoggedUserCartItems());
        model.addAttribute("calculatedPrice", calculateCartItemPrice.calculatePrice());
        return "cart";
    }

    @GetMapping("/buyBook/{bookId}")
    public String addBookToShoppingCart(@PathVariable(value = "bookId") Long bookId, Model model) {
        if (getLoggedUser.getLoggedUser() == null) {
            return "redirect:/login";
        }
        addCartItem.addCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/page/buyBook/{bookId}")
    public String addBookToShoppingCartFromSortedPage(@PathVariable(value = "bookId") Long bookId, ModelMap model) {
        if (getLoggedUser.getLoggedUser() == null) {
            return "redirect:/login";
        }
        addCartItem.addCartItem(bookId);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/deleteCartItem/{cartItemId}")
    public String deleteCartItemFromShoppingCart(@PathVariable(value = "cartItemId") Long cartItemId, ModelMap modelMap) {
        deleteCartItem.deleteCartItem(cartItemId);
        return "redirect:/shoppingCart";
    }
}
