package com.example.functionalBookstore.domain.cart.application;

import com.example.functionalBookstore.domain.cart.core.model.AddCustomerInfoCommand;
import com.example.functionalBookstore.domain.cart.core.ports.incoming.AddCustomerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customerInfo")
@RequiredArgsConstructor
public class CustomerInfoAddController {

    private final AddCustomerInfo addCustomerInfo;

    @GetMapping
    public String showAddCustomerInfoPage() {
        return "customerInfoAdd";
    }

    @ModelAttribute("customerInfo")
    public AddCustomerInfoCommand addCustomerInfoCommand() {
        return new AddCustomerInfoCommand();
    }

    @PostMapping
    public String AddCustomerInfo(@ModelAttribute("customerInfo") AddCustomerInfoCommand addCustomerInfoCommand) {
        addCustomerInfo.saveCustomerInfo(addCustomerInfoCommand);
        return "redirect:/customerInfo?success";
    }
}
