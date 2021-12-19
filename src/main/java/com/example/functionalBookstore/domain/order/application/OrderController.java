package com.example.functionalBookstore.domain.order.application;

import com.example.functionalBookstore.domain.order.core.ports.incoming.AddNewOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final AddNewOrder addNewOrder;

    @GetMapping("/saveOrder")
    public String showCart(ModelMap model) {
        addNewOrder.saveOrder();
        return "redirect:/finalizeOrder";
    }

    @GetMapping("/finalizeOrder")
    public String showSaveOrderInformation(ModelMap model) {
        return "orderFinalize";
    }
}
