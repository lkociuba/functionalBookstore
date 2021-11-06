package com.example.functionalBookstore.domain.user.application;

import com.example.functionalBookstore.domain.user.core.model.AddUserCommand;
import com.example.functionalBookstore.domain.user.core.ports.incoming.AddNewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserCommandController {

    private final AddNewUser addNewUser;

    @ModelAttribute("user")
    public AddUserCommand addUserCommand() {
        return new AddUserCommand();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") AddUserCommand addUserCommand) {
        addNewUser.save(addUserCommand);
        return "redirect:/registration?success";
    }
}